load("@rules_java//java:defs.bzl", "java_library", "java_test")
load("@rules_cc//cc:defs.bzl", "cc_binary", "cc_library")

genrule(
    name = "jni_headers",
    srcs = [
        "java/src/main/java/cz/adamh/utils/NativeUtils.java",
        "java/src/main/java/org/jitsi_modified/sctp4j/IncomingSctpDataHandler.java",
        "java/src/main/java/org/jitsi_modified/sctp4j/OutgoingSctpDataHandler.java",
        "java/src/main/java/org/jitsi_modified/sctp4j/SctpJni.java",
    ],
    outs = ["org_jitsi_modified_sctp4j_SctpJni.h"],
    cmd = """
        $(JAVABASE)/bin/javac -h $(RULEDIR) -d $(RULEDIR) $(execpath :java/src/main/java/cz/adamh/utils/NativeUtils.java) \
        $(execpath :java/src/main/java/org/jitsi_modified/sctp4j/IncomingSctpDataHandler.java) \
        $(execpath :java/src/main/java/org/jitsi_modified/sctp4j/OutgoingSctpDataHandler.java) \
        $(execpath :java/src/main/java/org/jitsi_modified/sctp4j/SctpJni.java)
    """,
    toolchains = ["@bazel_tools//tools/jdk:current_host_java_runtime"],
)

java_library(
    name = "jniwrapper",
    srcs = glob(["java/**/*.java"]),
    resources = ["//sctp/jniwrapper/native/src/main/resources/lib/linux:libjnisctp"],
    visibility = ["//visibility:public"],
    deps = [
        "//utils/jitsi-utils",
        "@maven//:junit_junit",
        "@maven//:org_bitlet_weupnp",
        "@maven//:org_jitsi_jain_sip_ri_ossonly",
        "@maven//:org_opentelecoms_sdp_java_sdp_nist_bridge",
        "@maven//:org_opentelecoms_sdp_sdp_api",
    ],
)

cc_library(
    name = "jnilib",
    srcs = [
        "native/src/org_jitsi_modified_sctp4j_SctpJni.c",
    ],
    hdrs = [
        ":jni_headers",
    ],
    include_prefix = ".",
    deps = [
        "//tools/jni:copy_jni_hdr_lib",
        "//usrsctp/usrsctplib",
    ],
)

cc_binary(
    name = "libjnisctp.so",
    linkshared = 1,
    visibility = ["//visibility:public"],
    deps = [
        ":jnilib",
    ],
)

java_test(
    name = "jni_smoketest",
    srcs = ["jnilib/src/test/java/SctpJniTest.java"],
    test_class = "org.jitsi_modified.sctp4j.SctpJni",
    deps = [
        ":jniwrapper",
    ],
)
