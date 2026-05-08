# test-case-kotlin in Kotlin

[![GitHub link](https://img.shields.io/badge/GitHub-KotlinMania%2Ftest--case--kotlin-blue.svg)](https://github.com/KotlinMania/test-case-kotlin)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.kotlinmania/test-case-kotlin)](https://central.sonatype.com/artifact/io.github.kotlinmania/test-case-kotlin)
[![Build status](https://img.shields.io/github/actions/workflow/status/KotlinMania/test-case-kotlin/ci.yml?branch=main)](https://github.com/KotlinMania/test-case-kotlin/actions)

This is a Kotlin Multiplatform line-by-line transliteration port of [`frondeus/test-case`](https://github.com/frondeus/test-case).

**Original Project:** This port is based on [`frondeus/test-case`](https://github.com/frondeus/test-case). All design credit and project intent belong to the upstream authors; this repository is a faithful port to Kotlin Multiplatform with no behavioural changes intended.

### Porting status

This is an **in-progress port**. The goal is feature parity with the upstream Rust crate while providing a native Kotlin Multiplatform API. Every Kotlin file carries a `// port-lint: source <path>` header naming its upstream Rust counterpart so the AST-distance tool can track provenance.

---

## Upstream README — `frondeus/test-case`

> The text below is reproduced and lightly edited from [`https://github.com/frondeus/test-case`](https://github.com/frondeus/test-case). It is the upstream project's own description and remains under the upstream authors' authorship; links have been rewritten to absolute upstream URLs so they continue to resolve from this repository.

[![Crates.io](https://img.shields.io/crates/v/test-case.svg)](https://crates.io/crates/test-case)
[![Crates.io](https://img.shields.io/crates/d/test-case.svg)](https://crates.io/crates/test-case)
[![Docs.rs](https://docs.rs/test-case/badge.svg)](https://docs.rs/test-case)
[![MIT License](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/rust-lang/docs.rs/master/LICENSE)
[![Build Status](https://github.com/frondeus/test-case/workflows/Test/badge.svg)](https://github.com/frondeus/test-case/actions)
![Maintenance](https://img.shields.io/badge/maintenance-activly--developed-brightgreen.svg)

## Test Case

## Overview
`test_case` crate provides procedural macro attribute that generates parametrized test instances.

## Getting Started

Crate has to be added as a dependency to `Cargo.toml`:

```toml
[dev-dependencies]
test-case = "*"
```

and imported to the scope of a block where it's being called
(since attribute name collides with rust's built-in `custom_test_frameworks`) via:

```rust
use test_case::test_case;
```

## Example usage:

```rust
#[cfg(test)]
mod tests {
    use test_case::test_case;

    #[test_case(-2, -4 ; "when both operands are negative")]
    #[test_case(2,  4  ; "when both operands are positive")]
    #[test_case(4,  2  ; "when operands are swapped")]
    fn multiplication_tests(x: i8, y: i8) {
        let actual = (x * y).abs();

        assert_eq!(8, actual)
    }
}
```

Output from `cargo test` for this example:

```sh
$ cargo test

running 4 tests
test tests::multiplication_tests::when_both_operands_are_negative ... ok
test tests::multiplication_tests::when_both_operands_are_positive ... ok
test tests::multiplication_tests::when_operands_are_swapped ... ok

test result: ok. 4 passed; 0 failed; 0 ignored; 0 measured; 0 filtered out
```

### Test Matrix

The `#[test_matrix(...)]` macro allows generating multiple test cases from the
Cartesian product of one or more possible values for each test function argument. The
number of arguments to the `test_matrix` macro must be the same as the number of arguments to
the test function. Each macro argument can be:

    1. A list in array (`[x, y, ...]`) or tuple (`(x, y, ...)`) syntax. The values can be any
       valid [expression](https://doc.rust-lang.org/reference/expressions.html).
    2. A closed numeric range expression (e.g. `0..100` or `1..=99`), which will generate
       argument values for all integers in the range.
    3. A single expression, which can be used to keep one argument constant while varying the
       other test function arguments using a list or range.

#### Example usage:

```rust
#[cfg(test)]
mod tests {
    use test_case::test_matrix;

    #[test_matrix(
        [-2, 2],
        [-4, 4]
    )]
    fn multiplication_tests(x: i8, y: i8) {
        let actual = (x * y).abs();

        assert_eq!(8, actual)
    }
}
```

## MSRV Policy

Starting with version 3.0 and up `test-case` introduces policy of only supporting latest stable Rust.
These changes may happen overnight, so if your stack is lagging behind current stable release,
it may be best to consider locking `test-case` version with `=` in your `Cargo.toml`.

## Documentation

Most up to date documentation is available in our [wiki](https://github.com/frondeus/test-case/wiki).

# License

Licensed under of MIT license ([LICENSE-MIT](https://github.com/frondeus/test-case/blob/HEAD/LICENSE-MIT) or https://opensource.org/licenses/MIT)

# Contributing

Project roadmap is available at [link](https://github.com/frondeus/test-case/issues/74). All contributions are welcome.

Recommended tools:
* `cargo readme` - to regenerate README.md based on template and lib.rs comments
* `cargo insta`  - to review test snapshots
* `cargo edit`   - to add/remove dependencies
* `cargo fmt`    - to format code
* `cargo clippy` - for all insights and tips
* `cargo fix`    - for fixing warnings

---

## About this Kotlin port

### Installation

```kotlin
dependencies {
    implementation("io.github.kotlinmania:test-case-kotlin:0.1.0")
}
```

### Building

```bash
./gradlew build
./gradlew test
```

### Targets

- macOS arm64
- Linux x64
- Windows mingw-x64
- iOS arm64 / simulator-arm64 (Swift export + XCFramework)
- JS (browser + Node.js)
- Wasm-JS (browser + Node.js)
- Android (API 24+)

### Porting guidelines

See [AGENTS.md](AGENTS.md) and [CLAUDE.md](CLAUDE.md) for translator discipline, port-lint header convention, and Rust → Kotlin idiom mapping.

### License

This Kotlin port is distributed under the same MIT license as the upstream [`frondeus/test-case`](https://github.com/frondeus/test-case). See [LICENSE](LICENSE) (and any sibling `LICENSE-*` / `NOTICE` files mirrored from upstream) for the full text.

Original work copyrighted by the test-case authors.  
Kotlin port: Copyright (c) 2026 Sydney Renee and The Solace Project.

### Acknowledgments

Thanks to the [`frondeus/test-case`](https://github.com/frondeus/test-case) maintainers and contributors for the original Rust implementation. This port reproduces their work in Kotlin Multiplatform; bug reports about upstream design or behavior should go to the upstream repository.
