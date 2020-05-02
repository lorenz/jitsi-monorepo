# Jitsi Monorepo

_Make Jitsi buildable using a Monorepo and Bazel_

## Motivation

Jitsi is extremely hard to build since they use lots of different repos each pushing Maven artifacts
into a [makeshift Maven repo in a GitHub repo](https://github.com/jitsi/jitsi-maven-repository),
which are then consumed by other repo's Maven builds.

They also have lots of JNI shared libraries checked into the repos with usually no good way to build
them yourself.

This is bad for developer productivity, security, auditability and reproducibility. So let's fix it.

## How

This repo is generated and managed using git subtree to include other repos with their history. This
makes it very nice to work with since you can do global code search, git blame (and its editor
integrations) give you useful information and both upstreaming patches and pulling down upstream work
is rather easy. For example updating libjitsi to upstream master can be done with
`git subtree pull -P libjitsi https://github.com/jitsi/libjitsi.git master`.

To build all the different parts I'm using Google's Bazel build system instead of Maven. This gives
us reproducible, hermetic and fast builds/tests. It also allows integration of different programming
languages (like C for JNI) into the same build tree.

## Status

This can currently build a videobridge fatjar and most libraries with zero dependencies. It's not
really useful for practical use at the moment. The main blocker is generating build targets for all
the parts of the main Jitsi (desktop) repository. But it is a starting point for further development.

Sadly I don't have more time to push this work to completion myself so I decided to just publish it
in the hopes that it's useful for someone. Also if it's not getting adopted by upstream it would be
pretty wasteful investing weeks into it. If you need help with something in this repo, feel free
to contact me at the address in my GitHub profile.

```
bazel run //videobridge
```

## Accomplishments

- Builds FMJ with traceable history (was previously checked-in as a compiled Maven artefact)
- Fixed bug in rules_kotlin (https://github.com/bazelbuild/rules_kotlin/pull/322)
- Made libjitsi and zrtp4j compatible (and build with) Bouncy Castle 1.65
- Minor fixes (and annotations) where Google's ErrorProne complained about obviously wrong code
- Bazel-based usrsctp build with static linking into the JNI library (no runtime usrsctp needed)
- JNI header generation with Bazel's hermetic toolchain
- Can build enough for a full videobridge
- videobridge does run in distroless containers

## Todo

- Make jitsi repo build (means dealing with OSGi)
- Make jicofo and jigasi build
- Integrate most tests
- Test all the code patches in here
- Use Bazel's configuration transitions to build the JNI libraries for all supported platforms in a
  standard build.
- Build the optional JNI shared objects (like OpenSSL and FFmpeg), means pulling in more C code

## Licensing

Most code in here is not mine and is covered under their respective licenses. My patches and build
system code are under CC0 (public domain or as close as you can get to it in your juristdiction).
