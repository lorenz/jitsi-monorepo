workspace(name = "jitsi_jitsi_videobridge")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")
load("@bazel_tools//tools/build_defs/repo:git.bzl", "git_repository")

RULES_JVM_EXTERNAL_TAG = "3.0"

RULES_JVM_EXTERNAL_SHA = "62133c125bf4109dfd9d2af64830208356ce4ef8b165a6ef15bbff7460b35c3a"

http_archive(
    name = "rules_jvm_external",
    sha256 = RULES_JVM_EXTERNAL_SHA,
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

maven_install(
    artifacts = [
        "junit:junit:4.12",
        "com.google.guava:guava:28.2-jre",
        "org.osgi:org.osgi.core:4.3.1",
        "org.jetbrains:annotations:15.0",
        "org.jetbrains.kotlin:kotlin-reflect:1.3.71",
        "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.71",
        "com.googlecode.json-simple:json-simple:1.1.1",
        "net.java.dev.jna:jna:5.5.0",
        "com.github.spotbugs:spotbugs-annotations:3.1.12",
        "org.igniterealtime.smack:smack-core:4.2.4",
        "org.igniterealtime.smack:smack-extensions:4.2.4",
        "org.igniterealtime.smack:smack-tcp:4.2.4",
        "org.apache.commons:commons-lang3:3.9",
        "org.bouncycastle:bcprov-jdk15on:1.65",
        "org.bouncycastle:bcpkix-jdk15on:1.65",
        "org.bouncycastle:bctls-jdk15on:1.65",
        "com.github.kotlin-graphics:kotlin-unsigned:v3.1.3",
        "org.opentelecoms.sdp:java-sdp-nist-bridge:1.1",
        "ch.imvs:sdes4j:1.1.4",
        "org.pcap4j:pcap4j-packetfactory-static:1.8.2",
        "org.pcap4j:pcap4j-core:1.8.2",
        "com.typesafe:config:1.3.4",
        "com.datadoghq:java-dogstatsd-client:2.5",
        "org.eclipse.jetty:jetty-proxy:9.4.15.v20190215",
        "org.eclipse.jetty:jetty-server:9.4.15.v20190215",
        "org.eclipse.jetty:jetty-servlet:9.4.15.v20190215",
        "org.eclipse.jetty:jetty-util:9.4.15.v20190215",
        "org.xeustechnologies:jcl-core:2.8",
        "org.igniterealtime:tinder:1.3.0",
        "org.igniterealtime.whack:core:2.0.1",
        "org.glassfish.jersey.containers:jersey-container-jetty-http:2.30.1",
        "org.glassfish.jersey.containers:jersey-container-servlet:2.30.1",
        "org.glassfish.jersey.inject:jersey-hk2:2.30.1",
        "org.glassfish.jersey.media:jersey-media-json-jackson:2.30.1",
        "org.javassist:javassist:3.22.0-CR2",
        "org.bitlet:weupnp:0.1.4",
    ],
    fetch_sources = True,
    repositories = [
        "http://uk.maven.org/maven2",
        "https://jcenter.bintray.com/",
        "https://jitpack.io",
    ],
)

rules_kotlin_version = "fix_underscore_package_names"

rules_kotlin_sha = "049590f74b2c928b44543bcdf9503bac09b7030e7e1bc0a3c4b7d7da32c61cef"

http_archive(
    name = "io_bazel_rules_kotlin",
    sha256 = rules_kotlin_sha,
    strip_prefix = "rules_kotlin-%s" % rules_kotlin_version,
    type = "zip",
    urls = ["https://github.com/lorenz/rules_kotlin/archive/%s.zip" % rules_kotlin_version],
)

load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kotlin_repositories", "kt_register_toolchains")

kotlin_repositories()

kt_register_toolchains()

http_archive(
    name = "fmj",
    build_file = "@//:fmj.BUILD",
    sha256 = "21874d5e34f312d3f4b52abed192a13f562bc1a21a479bff5a2307a9095b060f",
    strip_prefix = "fmj-code-r122/",
    urls = [
        "https://blob.dolansoft.org/public/fmj-code-r122.zip",
        "https://sourceforge.net/code-snapshots/svn/f/fm/fmj/code/fmj-code-r122.zip",
    ],
)
