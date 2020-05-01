load("@rules_java//java:defs.bzl", "java_binary", "java_library", "java_test")

package(default_visibility = ["//visibility:public"])

java_library(
    name = "gpl-dependencies",
    srcs = glob(
        include = ["src/main/java/org/jitsi/**/*.java"],
    ),
    deps = [
        "@maven//:net_java_dev_jna_jna",
    ],
)
