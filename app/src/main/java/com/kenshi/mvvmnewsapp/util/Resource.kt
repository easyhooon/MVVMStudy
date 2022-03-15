package com.kenshi.mvvmnewsapp.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    //여기에 지정한 클래스들만 Resource 클래스를 상속가능하다. 다른 클래스들은 불가능

    //success 클래스의 data는 not null 을 보장
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}