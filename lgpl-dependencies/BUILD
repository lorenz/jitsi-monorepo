load("@rules_java//java:defs.bzl", "java_binary", "java_library", "java_test")

package(default_visibility = ["//visibility:public"])

java_library(
    name = "lgpl-dependencies",
    srcs = glob(
        include = ["src/main/java/org/jitsi/**/*.java"],
    ),
    deps = [
        "//utils/jitsi-utils",
        "@maven//:net_java_dev_jna_jna",
    ],
)
