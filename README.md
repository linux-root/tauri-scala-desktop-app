# Tyrian Tauri Scala

A desktop application built with Tauri, Scala.js, and Tyrian - combining the power of Scala's type safety with Tauri's native desktop capabilities.

## Prerequisites

- [Rust](https://www.rust-lang.org/tools/install)
- [Bun](https://bun.sh)
- [SBT](https://www.scala-sbt.org/download.html)
- 
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
<img width="3254" height="1980" alt="image" src="https://github.com/user-attachments/assets/8126e0f9-8dc4-4383-b11e-bf2ea003cbf2" />
<img width="2200" height="1538" alt="image" src="https://github.com/user-attachments/assets/cb733873-dfd5-4750-94ab-07238820fa91" />


## Tech Stack

- **Tauri** - Desktop app framework
- **Scala.js** - Scala to JavaScript compiler
- **Tyrian** - Functional UI framework
- **Vite** - Frontend build tool
