package com.example.testcrash.shared


import platform.Foundation.NSURLSessionDataDelegateProtocol
import platform.UIKit.UIDevice
import platform.darwin.NSObject

actual class Platform actual constructor() {

    init {
        val delegate = object : NSObject(), NSURLSessionDataDelegateProtocol {

        }
    }

    actual val platform: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}