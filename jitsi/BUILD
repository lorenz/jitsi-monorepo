load("@rules_java//java:defs.bzl", "java_library")

package(default_visibility = ["//visibility:public"])

java_library(
    name = "configuration",
    srcs = glob(["src/net/java/sip/communicator/impl/configuration/**/*.java"]),
    deps = [
        "//libjitsi",
        "//utils/jitsi-utils",
        "@maven//:com_google_guava_guava",
        "@maven//:net_java_dev_jna_jna",
        "@maven//:org_apache_commons_commons_lang3",
        "@maven//:org_osgi_org_osgi_core",
    ],
)
