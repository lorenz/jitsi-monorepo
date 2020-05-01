load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

kt_jvm_library(
    name = "jitsi-utils-kotlin",
    srcs = glob([
        "src/main/kotlin/org/jitsi/**/*.kt",
        "src/main/kotlin/org/jitsi/**/*.java",
    ]),
    visibility = ["//visibility:public"],
    deps = [
        "//utils/jitsi-utils",
    ],
)
