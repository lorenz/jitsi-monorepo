load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "sctp",
    srcs = glob(
        include = ["src/main/java/**/*.java"],
    ),
    visibility = ["//visibility:public"],
    deps = [
        "//sctp/jniwrapper",
    ],
)
