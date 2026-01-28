# Tyrian Tauri Scala

A desktop application built with Tauri, Scala.js, and Tyrian - combining the power of Scala's type safety with Tauri's native desktop capabilities.

## Prerequisites

- [Rust](https://www.rust-lang.org/tools/install)
- [Bun](https://bun.sh)
- [SBT](https://www.scala-sbt.org/download.html)
- [Tauri Prerequisites](https://tauri.app/v1/guides/getting-started/prerequisites)

## Development

Run the development server with hot reload:

```bash
sbt dev
```

## Build

Build the macOS application (macOS only for now):

```bash
sbt mac
```

The built application will be available in `src-tauri/target/release/bundle/`.

## Tech Stack

- **Tauri** - Desktop app framework
- **Scala.js** - Scala to JavaScript compiler
- **Tyrian** - Functional UI framework
- **Vite** - Frontend build tool
