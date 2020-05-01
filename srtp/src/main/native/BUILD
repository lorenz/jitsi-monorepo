package(default_visibility = ["//visibility:public"])

cc_library(
    name = "srtp",
    srcs = ["Hmac.c", "OpenSslWrapperLoader.c", "SrtpCipherCtrOpenSsl.c"],
    deps = ["//tools/jni:copy_jni_hdr_lib"],
    alwayslink = True,
)

cc_binary(
    name = "libjitsisrtp.so",
    linkshared = True,
    deps = [":srtp"],
)

