load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

kt_jvm_library(
    name = "jicoco-kotlin",
    srcs = glob([
        "src/main/kotlin/**/*.kt",
        "src/main/java/**/*.java",
    ]),
    visibility = ["//visibility:public"],
    deps = [
        "//jicoco/jicoco",
        "//osgi",
        "//utils/jitsi-utils",
        "//utils/jitsi-utils-kotlin",
    ],
)
