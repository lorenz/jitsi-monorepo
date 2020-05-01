load("@rules_java//java:defs.bzl", "java_binary", "java_library", "java_test")

package(default_visibility = ["//visibility:public"])

java_library(
    name = "ice4j",
    srcs = glob(["src/main/java/**/*.java"]),
    deps = [
        "//utils/jitsi-utils",
        "@maven//:org_bitlet_weupnp",
        "@maven//:org_jitsi_jain_sip_ri_ossonly",
        "@maven//:org_opentelecoms_sdp_java_sdp_nist_bridge",
        "@maven//:org_opentelecoms_sdp_sdp_api",
    ],
)
