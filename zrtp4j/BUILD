load("@rules_java//java:defs.bzl", "java_binary", "java_library", "java_test")

package(default_visibility = ["//visibility:public"])

java_library(
    name = "zrtp4j",
    srcs = glob(
        include = [
            "src/gnu/java/**",
            "src/djb/**",
            "src/org/bouncycastle/**",
        ],
        exclude = [
            "src/gnu/java/bigintcrypto/test/**",
            "src/gnu/java/zrtp/jmf/**",
        ],
    ),
    deps = [
        "//bccontrib",
        "@fmj",
        "@maven//:org_bouncycastle_bcprov_jdk15on",
    ],
)

#java_test(
#    name = "tests",
#    srcs = glob(["src/test/java/org/jitsi/**/*.java"]),
#    test_class = "org.jitsi.impl.osgi.framework.OSGiTest",
#    deps = [
#        ":jitsi-utils",
#	"@maven//:org_osgi_org_osgi_core",
#        "@maven//:junit_junit",
#    ],
#)
