package com.custom.transportation.base

abstract class BaseModel {
    abstract var presenter: BaseContract.Presenter

    abstract fun search(search: String)
}