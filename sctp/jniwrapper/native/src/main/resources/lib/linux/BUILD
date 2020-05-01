# Terrible hack because of https://github.com/bazelbuild/bazel/issues/97
genrule(
    name = "libjnisctp",
    srcs = ["//sctp/jniwrapper:libjnisctp.so"],
    outs = [
        "libjnisctp.so",
    ],
    cmd = "cp -f $(execpaths //sctp/jniwrapper:libjnisctp.so) $(RULEDIR)",
    visibility = ["//visibility:public"],
)
