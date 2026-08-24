# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 1/2 (50.0%)
- **Function parity:** 0/20 matched (target 6) — 0.0%
- **Class/type parity:** 0/0 matched (target 1) — N/A
- **Combined symbol parity:** 0/20 matched (target 7) — 0.0%
- **Average inline-code cosine:** 0.00 (function body across 1 matched files)
- **Average documentation cosine:** 0.29 (doc text across 1 matched files)
- **Cheat-zeroed Files:** 1
- **Critical Issues:** 1 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. lib

- **Target:** `testcase.Case [ZERO] [PROVENANCE-FALLBACK]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched (target 6)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `lib.rs` vs expected `lib.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `lib.rs` vs expected `lib.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `lib.rs` vs expected `lib.rs`
- **Proposed provenance header:** `// port-lint: source lib.rs` (current: `// port-lint: source lib.rs`)
- **Proposed provenance header:** `// port-lint: source lib.rs` (current: `// port-lint: source lib.rs`)
- **Proposed provenance header:** `// port-lint: source lib.rs` (current: `// port-lint: source lib.rs`)
- **Lint issues:** 3

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

