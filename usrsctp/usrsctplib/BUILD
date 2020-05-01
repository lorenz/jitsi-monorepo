load("@rules_cc//cc:defs.bzl", "cc_library")

package(default_visibility = ["//visibility:public"])

cc_library(
    name = "usrsctplib",
    srcs = glob(
        include = [
            "netinet/*.c",
            "netinet/*.h",
            "netinet6/*.c",
            "netinet6/*.h",
            "*.c",
            "*.h",
        ],
        exclude = [
            "usrsctp.h",
        ],
    ),
    hdrs = ["usrsctp.h"],
    defines = [
        "__Userspace_os_Linux",
        "__Userspace__",
        "SCTP_SIMPLE_ALLOCATOR",
        "SCTP_PROCESS_LEVEL_LOCKS",
        "_GNU_SOURCE",
        "HAVE_SYS_QUEUE_H",
        "HAVE_LINUX_IF_ADDR_H",
        "HAVE_LINUX_RTNETLINK_H",
        "HAVE_NETINET_IP_ICMP_H",
    ],
    includes = [
        ".",
    ],
)
