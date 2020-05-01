load("@rules_java//java:defs.bzl", "java_library")

package(default_visibility = ["//visibility:public"])

java_library(
    name = "fmj",
    srcs = glob(
        include = [
            "fmj/src/**/*.java",
            "fmj/src.rtp/**/*.java",
            "fmj/src.stubs/**/*.java",
            "fmj/src.utils/**/*.java",
            "fmj/src.ejmf/**/*.java",
            "fmj/src.sunibm.base/**/*.java",
            "fmj/src.sunibm.replace/**/*.java",
            "fmj/src.capture/**/*.java",
        ],
        exclude = [
            "fmj/src/net/sf/fmj/gui/**",
            "fmj/src.ejmf/net/sf/fmj/ejmf/toolkit/gui/**",
            "**/civil/**",
            "**/net/sf/fmj/media/cdp/GlobalCaptureDevicePlugger.java",
            "**/net/sf/fmj/capture/**",
            "**/net/sf/fmj/ds/**",
            "**/net/sf/fmj/gui/**",
            "**/net/sf/fmj/test/**",
        ],
    ),
    deps = [],
)