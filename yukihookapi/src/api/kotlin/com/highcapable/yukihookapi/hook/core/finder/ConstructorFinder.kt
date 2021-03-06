/*
 * YukiHookAPI - An efficient Kotlin version of the Xposed Hook API.
 * Copyright (C) 2019-2022 HighCapable
 * https://github.com/fankes/YukiHookAPI
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * This file is Created by fankes on 2022/2/4.
 */
@file:Suppress("unused", "MemberVisibilityCanBePrivate", "UNCHECKED_CAST", "KotlinConstantConditions")

package com.highcapable.yukihookapi.hook.core.finder

import com.highcapable.yukihookapi.annotation.YukiPrivateApi
import com.highcapable.yukihookapi.hook.bean.VariousClass
import com.highcapable.yukihookapi.hook.core.YukiMemberHookCreater
import com.highcapable.yukihookapi.hook.core.finder.base.BaseFinder
import com.highcapable.yukihookapi.hook.core.finder.type.ModifierRules
import com.highcapable.yukihookapi.hook.core.reflex.tools.ReflectionTool
import com.highcapable.yukihookapi.hook.factory.hasExtends
import com.highcapable.yukihookapi.hook.log.yLoggerW
import com.highcapable.yukihookapi.hook.type.defined.UndefinedType
import com.highcapable.yukihookapi.hook.utils.runBlocking
import java.lang.reflect.Constructor

/**
 * [Constructor] ?????????
 *
 * ?????????????????????????????????????????????
 * @param hookInstance ?????? Hook ?????? - ???????????????????????? [YukiMemberHookCreater.MemberHookCreater.member]
 * @param classSet ????????????????????? [Class] ??????
 */
class ConstructorFinder @PublishedApi internal constructor(
    @property:YukiPrivateApi
    override val hookInstance: YukiMemberHookCreater.MemberHookCreater? = null,
    @property:YukiPrivateApi
    override val classSet: Class<*>? = null
) : BaseFinder(tag = "Constructor", hookInstance, classSet) {

    /** ??????????????? [classSet] */
    private var usedClassSet = classSet

    /** ???????????????????????????????????? [classSet] ?????????????????? */
    private var isFindInSuperClass = false

    /** ??????????????????????????? */
    private var remedyPlansCallback: (() -> Unit)? = null

    /** [Constructor] ???????????? */
    private var paramTypes: Array<out Class<*>>? = null

    /** [ModifierRules] ?????? */
    @PublishedApi
    internal var modifiers: ModifierRules? = null

    /**
     * ?????? [Constructor] ????????????
     *
     * ?????????????????? [param] ????????????????????????????????????????????????????????????
     *
     * ?????????????????????????????????????????? [param]
     */
    var paramCount = -1

    /**
     * ?????? [Constructor] ?????????????????????
     *
     * - ??????????????? [BaseFinder.IndexTypeCondition] ????????? [order] ????????????????????????
     * @param initiate ?????????
     * @return [BaseFinder.IndexTypeCondition]
     */
    inline fun modifiers(initiate: ModifierRules.() -> Unit): IndexTypeCondition {
        modifiers = ModifierRules().apply(initiate)
        return IndexTypeCondition(IndexConfigType.MATCH)
    }

    /**
     * ?????? [Constructor] ?????????????????????
     *
     * @return [BaseFinder.IndexTypeCondition]
     */
    fun emptyParam() = paramCount(num = 0)

    /**
     * ?????? [Constructor] ??????
     *
     * ????????????????????? [paramCount] ??? [paramTypes] ?????????????????? [paramCount] ????????????
     *
     * - ????????? [Constructor] ????????? [emptyParam] ??????????????????
     *
     * - ????????? [Constructor] ?????????????????????????????????????????? [paramCount] ????????????
     *
     * - ??????????????? [BaseFinder.IndexTypeCondition] ????????? [order] ????????????????????????
     * @param paramType ?????????????????? - ???????????? [Class]???[String]???[VariousClass]
     * @return [BaseFinder.IndexTypeCondition]
     */
    fun param(vararg paramType: Any): IndexTypeCondition {
        if (paramType.isEmpty()) error("paramTypes is empty, please use emptyParam() instead")
        paramTypes = ArrayList<Class<*>>().apply { paramType.forEach { add(it.compat() ?: UndefinedType) } }.toTypedArray()
        return IndexTypeCondition(IndexConfigType.MATCH)
    }

    /**
     * ??????????????????????????????
     * @return [BaseFinder.IndexTypeCondition]
     */
    fun order() = IndexTypeCondition(IndexConfigType.ORDER)

    /**
     * ?????? [Constructor] ????????????
     *
     * ?????????????????? [param] ????????????????????????????????????????????????????????????
     *
     * ?????????????????????????????????????????? [param]
     *
     * - ??????????????? [BaseFinder.IndexTypeCondition] ????????? [order] ????????????????????????
     * @param num ??????
     * @return [BaseFinder.IndexTypeCondition]
     */
    fun paramCount(num: Int): IndexTypeCondition {
        paramCount = num
        return IndexTypeCondition(IndexConfigType.MATCH)
    }

    /**
     * ????????? [classSet] ?????????????????????????????? [Constructor]
     *
     * - ???????????? [classSet] ?????????????????????????????? - API ????????????????????????????????? [Any] ?????????????????????
     * @param isOnlySuperClass ?????????????????? [classSet] ?????????????????? - ???????????? [Any] ???????????????
     */
    fun superClass(isOnlySuperClass: Boolean = false) {
        isFindInSuperClass = true
        if (isOnlySuperClass && classSet?.hasExtends == true) usedClassSet = classSet.superclass
    }

    /**
     * ??????????????????
     * @return [Constructor]
     * @throws NoSuchMethodError ???????????????????????????
     */
    private val result
        get() = ReflectionTool.findConstructor(
            usedClassSet, orderIndex, matchIndex, modifiers,
            paramCount, paramTypes, isFindInSuperClass
        )

    /**
     * ????????????
     * @param isBind ?????????????????????????????? [YukiMemberHookCreater.MemberHookCreater]
     * @param constructor ??????????????? [Constructor]
     */
    private fun setInstance(isBind: Boolean, constructor: Constructor<*>) {
        memberInstance = constructor
        if (isBind) hookInstance?.member = constructor
    }

    /**
     * ????????????????????????
     *
     * - ??????????????????????????????????????? - ?????????????????????????????????
     * @return [Result]
     */
    @YukiPrivateApi
    override fun build(isBind: Boolean) = try {
        if (classSet != null) {
            runBlocking {
                isBindToHooker = isBind
                setInstance(isBind, result)
            }.result { onHookLogMsg(msg = "Find Constructor [${memberInstance}] takes ${it}ms [${hookTag}]") }
            Result()
        } else Result(isNoSuch = true, Throwable("classSet is null"))
    } catch (e: Throwable) {
        onFailureMsg(throwable = e)
        Result(isNoSuch = true, e)
    }

    /**
     * ????????????????????????
     *
     * - ??????????????????????????????????????? - ?????????????????????????????????
     * @param throwable ??????
     * @return [Result]
     */
    @YukiPrivateApi
    override fun failure(throwable: Throwable?) = Result(isNoSuch = true, throwable)

    /**
     * [Constructor] ??????????????????
     *
     * ???????????????????????????????????????
     */
    inner class RemedyPlan @PublishedApi internal constructor() {

        /** ???????????????????????? */
        @PublishedApi
        internal val remedyPlans = HashSet<Pair<ConstructorFinder, Result>>()

        /**
         * ??????????????????????????? [Constructor]
         *
         * ??????????????????????????????????????? - ??????????????????
         *
         * ????????????????????? - ????????????????????????????????????
         * @param initiate ?????????
         */
        inline fun constructor(initiate: ConstructorFinder.() -> Unit) =
            Result().apply { remedyPlans.add(Pair(ConstructorFinder(hookInstance, classSet).apply(initiate), this)) }

        /** ??????????????? */
        @PublishedApi
        internal fun build() {
            if (classSet == null) return
            if (remedyPlans.isNotEmpty()) run {
                var isFindSuccess = false
                var lastError: Throwable? = null
                remedyPlans.forEachIndexed { p, it ->
                    runCatching {
                        runBlocking {
                            setInstance(isBindToHooker, it.first.result)
                        }.result {
                            onHookLogMsg(msg = "Find Constructor [${memberInstance}] takes ${it}ms [${hookTag}]")
                        }
                        isFindSuccess = true
                        it.second.onFindCallback?.invoke(memberInstance as Constructor<*>)
                        remedyPlansCallback?.invoke()
                        onHookLogMsg(msg = "Constructor [${memberInstance}] trying ${p + 1} times success by RemedyPlan [${hookTag}]")
                        return@run
                    }.onFailure {
                        lastError = it
                        onFailureMsg(msg = "Trying ${p + 1} times by RemedyPlan --> $it", isAlwaysPrint = true)
                    }
                }
                if (isFindSuccess.not()) {
                    onFailureMsg(
                        msg = "Trying ${remedyPlans.size} times and all failure by RemedyPlan",
                        throwable = lastError,
                        isAlwaysPrint = true
                    )
                    remedyPlans.clear()
                }
            } else yLoggerW(msg = "RemedyPlan is empty, forgot it? [${hookTag}]")
        }

        /**
         * [RemedyPlan] ???????????????
         *
         * ???????????????????????????????????????
         */
        inner class Result @PublishedApi internal constructor() {

            /** ???????????????????????? */
            internal var onFindCallback: (Constructor<*>.() -> Unit)? = null

            /**
             * ??????????????????
             * @param initiate ??????
             */
            fun onFind(initiate: Constructor<*>.() -> Unit) {
                onFindCallback = initiate
            }
        }
    }

    /**
     * [Constructor] ?????????????????????
     * @param isNoSuch ?????????????????????????????? - ?????????
     * @param e ????????????
     */
    inner class Result internal constructor(
        @PublishedApi internal val isNoSuch: Boolean = false,
        @PublishedApi internal val e: Throwable? = null
    ) {

        /**
         * ?????????????????????????????????
         * @param initiate ?????????
         * @return [Result] ?????????????????????
         */
        inline fun result(initiate: Result.() -> Unit) = apply(initiate)

        /**
         * ?????? [Constructor] ???????????????
         *
         * - ?????? [memberInstance] ???????????????????????????????????????????????????
         *
         * - ?????????????????? [remedys] ????????? [wait] ??????????????????
         * @return [Instance]
         */
        fun get() = Instance()

        /**
         * ????????????????????????
         * @return [Constructor] or null
         */
        fun give() = memberInstance as? Constructor<*>?

        /**
         * ?????? [Constructor] ???????????????
         *
         * - ?????????????????? [remedys] ???????????????????????????????????????
         *
         * - ????????????????????? [remedys] ???????????????????????????
         * @param initiate ?????? [Instance]
         */
        fun wait(initiate: Instance.() -> Unit) {
            if (memberInstance != null) initiate(get())
            else remedyPlansCallback = { initiate(get()) }
        }

        /**
         * ?????????????????????????????????
         *
         * ??????????????????????????????????????????????????????????????????
         *
         * ???????????? [RemedyPlan] ??????????????? - ????????????????????? [onNoSuchConstructor] ????????????????????????????????????
         * @param initiate ?????????
         * @return [Result] ?????????????????????
         */
        inline fun remedys(initiate: RemedyPlan.() -> Unit): Result {
            isUsingRemedyPlan = true
            if (isNoSuch) RemedyPlan().apply(initiate).build()
            return this
        }

        /**
         * ??????????????????????????????
         *
         * ???????????????????????????????????? - ???????????? [RemedyPlan] ???????????????
         * @param result ????????????
         * @return [Result] ?????????????????????
         */
        inline fun onNoSuchConstructor(result: (Throwable) -> Unit): Result {
            if (isNoSuch) result(e ?: Throwable())
            return this
        }

        /**
         * ?????????????????????????????????
         *
         * - ??? [isNotIgnoredNoSuchMemberFailure] ??? false ???????????????
         * @return [Result] ?????????????????????
         */
        fun ignoredError(): Result {
            isShutErrorPrinting = true
            return this
        }

        /**
         * [Constructor] ???????????????
         *
         * ????????????????????????????????????
         *
         * - ???????????? [get] ??? [wait] ??????????????? [Instance]
         */
        inner class Instance internal constructor() {

            /**
             * ????????????????????????????????????
             * @param param ??????????????????
             * @return [Any] or null
             */
            private fun baseCall(vararg param: Any?) =
                if (param.isNotEmpty())
                    (memberInstance as? Constructor<*>?)?.newInstance(*param)
                else (memberInstance as? Constructor<*>?)?.newInstance()

            /**
             * ???????????????????????????????????? - ???????????????????????????
             * @param param ??????????????????
             * @return [Any] or null
             */
            fun call(vararg param: Any?) = baseCall(*param)

            /**
             * ???????????????????????????????????? - ?????? [T] ??????????????????
             * @param param ??????????????????
             * @return [T] or null
             */
            fun <T> newInstance(vararg param: Any?) = baseCall(*param) as? T?

            override fun toString() = "[${(memberInstance as? Constructor<*>?)?.name ?: "<empty>"}]"
        }
    }
}