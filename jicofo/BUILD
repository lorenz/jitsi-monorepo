load("@rules_java//java:defs.bzl", "java_binary")

package(default_visibility = ["//visibility:public"])

java_binary(
    name = "jicofo",
    srcs = glob(["src/main/**/*.java"]),
    main_class = "org.jitsi.jicofo.Main",
    deps = [
        "//jicoco/jicoco",
        "//jicoco/jicoco-kotlin",
        "//osgi",
        "//utils/jitsi-utils",
        "//xmpp-extensions",
        "//jitsi:sip-communicator",
        "@maven//:com_googlecode_json_simple_json_simple",
        "@maven//:dom4j_dom4j",
        "@maven//:jakarta_ws_rs_jakarta_ws_rs_api",
        "@maven//:javax_servlet_javax_servlet_api",
        "@maven//:org_eclipse_jetty_jetty_server",
        "@maven//:org_eclipse_jetty_jetty_servlet",
        "@maven//:org_eclipse_jetty_jetty_util",
        "@maven//:org_glassfish_hk2_external_jakarta_inject",
        "@maven//:org_glassfish_hk2_hk2_api",
        "@maven//:org_glassfish_jersey_containers_jersey_container_jetty_http",
        "@maven//:org_glassfish_jersey_containers_jersey_container_servlet",
        "@maven//:org_glassfish_jersey_containers_jersey_container_servlet_core",
        "@maven//:org_glassfish_jersey_core_jersey_common",
        "@maven//:org_glassfish_jersey_core_jersey_server",
        "@maven//:org_glassfish_jersey_inject_jersey_hk2",
        "@maven//:org_glassfish_jersey_media_jersey_media_json_jackson",
        "@maven//:org_igniterealtime_smack_smack_core",
        "@maven//:org_igniterealtime_smack_smack_extensions",
        "@maven//:org_igniterealtime_smack_smack_tcp",
        "@maven//:org_igniterealtime_tinder",
        "@maven//:org_igniterealtime_whack_core",
        "@maven//:org_jetbrains_annotations",
        "@maven//:org_jxmpp_jxmpp_core",
        "@maven//:org_jxmpp_jxmpp_jid",
        "@maven//:org_osgi_org_osgi_core",
        "@maven//:xpp3_xpp3",
        # javax.servlet
        # jitsi-configuration
        # jitsi-credentialstorage
        # jitsi-protocol-jabber
        # jitsi-util
        # slf4j
    ],
)
