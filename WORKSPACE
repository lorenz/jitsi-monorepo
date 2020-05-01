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
        "com.googlecode.json-simple:json-simple:1.1.1",
        "net.java.dev.jna:jna:5.5.0",
        "com.github.spotbugs:spotbugs-annotations:3.1.12",
        "org.igniterealtime.smack:smack-core:4.2.4",
        "org.igniterealtime.smack:smack-extensions:4.2.4",
        "org.apache.commons:commons-lang3:3.9",
        "org.bouncycastle:bcprov-jdk15on:1.55",
        "org.bouncycastle:bcpkix-jdk15on:1.55",
        "com.github.kotlin-graphics:kotlin-unsigned:v3.1.3",
        "org.opentelecoms.sdp:java-sdp-nist-bridge:1.1",
        "ch.imvs:sdes4j:1.1.4",
    ],
    fetch_sources = True,
    repositories = [
        "http://uk.maven.org/maven2",
        "https://jcenter.bintray.com/",
        "https://jitpack.io",
    ],
)

rules_kotlin_version = "legacy-1.3.0"

rules_kotlin_sha = "4fd769fb0db5d3c6240df8a9500515775101964eebdf85a3f9f0511130885fde"

http_archive(
    name = "io_bazel_rules_kotlin",
    sha256 = rules_kotlin_sha,
    strip_prefix = "rules_kotlin-%s" % rules_kotlin_version,
    type = "zip",
    urls = ["https://github.com/bazelbuild/rules_kotlin/archive/%s.zip" % rules_kotlin_version],
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
