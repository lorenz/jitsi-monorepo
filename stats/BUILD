load("@rules_java//java:defs.bzl", "java_binary", "java_library", "java_test")

package(default_visibility = ["//visibility:public"])

java_library(
    name = "stats",
    srcs = glob(["src/main/java/**/*.java"]),
    deps = [
        "//utils/jitsi-utils",
        "@maven//:com_google_code_gson_gson",
        "@maven//:io_callstats_callstats_java_sdk",
        "@maven//:org_bitbucket_b_c_jose4j",
        "@maven//:org_osgi_org_osgi_core",
    ],
)
