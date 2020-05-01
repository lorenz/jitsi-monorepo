
workspace(name = "jitsi_jitsi_videobridge")
load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")
load("@bazel_tools//tools/build_defs/repo:git.bzl","git_repository")

RULES_JVM_EXTERNAL_TAG = "3.0"
RULES_JVM_EXTERNAL_SHA = "62133c125bf4109dfd9d2af64830208356ce4ef8b165a6ef15bbff7460b35c3a"

http_archive(
    name = "rules_jvm_external",
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    sha256 = RULES_JVM_EXTERNAL_SHA,
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
    ],
    fetch_sources = True,
    repositories = [
        "http://uk.maven.org/maven2",
        "https://jcenter.bintray.com/",
    ],
)

