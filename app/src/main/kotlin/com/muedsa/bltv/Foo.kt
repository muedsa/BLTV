package com.muedsa.bltv

import javax.inject.Inject

class Foo @Inject constructor() {

    val name: String

    companion object {
        @JvmStatic
        var id: Int = 0
    }

    init {
        synchronized(Foo) {
            id += 1
            name = "Foo $id"
        }
    }
}