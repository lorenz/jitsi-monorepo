load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

kt_jvm_library(
    name = "media-transform",
    srcs = glob([
        "src/main/**/*.kt",
        "src/main/**/*.java",
    ]),
    visibility = ["//visibility:public"],
    deps = [
        "//jicoco/jicoco-kotlin",
        "//rtp",
        "//srtp",
        "//utils/jitsi-utils",
        "//utils/jitsi-utils-kotlin",
        "@maven//:org_bouncycastle_bcpkix_jdk15on",
        "@maven//:org_bouncycastle_bcprov_jdk15on",
        "@maven//:org_bouncycastle_bctls_jdk15on",
        "@maven//:org_pcap4j_pcap4j_core",
        "@maven//:org_pcap4j_pcap4j_packetfactory_static",
    ],
)
