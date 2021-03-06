## YukiHookDataChannel [class]

```kotlin
class YukiHookDataChannel private constructor()
```

**变更记录**

`v1.0.88` `新增`

**功能描述**

> 实现 Xposed 模块的数据通讯桥。

通过模块与宿主相互注册 `BroadcastReceiver` 来实现数据的交互。

模块需要将 `Application` 继承于 `ModuleApplication` 来实现此功能。

!> 模块与宿主需要保持存活状态，否则无法建立通讯。

### NameSpace [class]

```kotlin
inner class NameSpace internal constructor(private val context: Context?, private val packageName: String, private val isSecure: Boolean)
```

**变更记录**

`v1.0.88` `新增`

`v1.0.90` `修改`

新增 `isSecure` 参数

**功能描述**

> `YukiHookDataChannel` 命名空间。

#### with [method]

```kotlin
inline fun with(initiate: NameSpace.() -> Unit): NameSpace
```

**变更记录**

`v1.0.88` `新增`

**功能描述**

> 创建一个调用空间。

#### put [method]

```kotlin
fun <T> put(key: String, value: T)
```

```kotlin
fun <T> put(data: ChannelData<T>, value: T?)
```

```kotlin
fun put(vararg data: ChannelData<*>)
```

**变更记录**

`v1.0.88` `新增`

**功能描述**

> 发送键值数据。

#### put [method]

```kotlin
fun put(key: String)
```

**变更记录**

`v1.0.88` `新增`

**功能描述**

> 仅发送键值监听，使用默认值 `VALUE_WAIT_FOR_LISTENER` 发送键值数据。

#### wait [method]

```kotlin
fun <T> wait(key: String, result: (value: T) -> Unit)
```

```kotlin
fun <T> wait(data: ChannelData<T>, result: (value: T) -> Unit)
```

**变更记录**

`v1.0.88` `新增`

`v1.0.90` `修改`

移除默认值 `value`

**功能描述**

> 获取键值数据。

#### wait [method]

```kotlin
fun wait(key: String, callback: () -> Unit)
```

**变更记录**

`v1.0.88` `新增`

**功能描述**

> 仅获取监听结果，不获取键值数据。

!> 仅限使用 `VALUE_WAIT_FOR_LISTENER` 发送的监听才能被接收。

#### checkingVersionEquals [method]

```kotlin
fun checkingVersionEquals(result: (Boolean) -> Unit)
```

**变更记录**

`v1.0.88` `新增`

**功能描述**

> 获取模块与宿主的版本是否匹配。

通过此方法可原生判断 Xposed 模块更新后宿主并未重新装载造成两者不匹配的情况。