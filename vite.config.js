import { defineConfig } from 'vite';
import path from 'path';

const scalaVersion = '3.8.1'
const scalaProjectName = 'tyrian-tauri-scala'

// @ts-expect-error process is a nodejs global
const host = process.env.TAURI_DEV_HOST;

export default defineConfig(({ command }) => {
  return {
    resolve: {
      alias: {
        "scalajs": command == "serve" ? `./target/scala-${scalaVersion}/${scalaProjectName}-fastopt/main.js` : `./target/scala-${scalaVersion}/${scalaProjectName}-opt/main.js`,
        "resources": path.resolve(__dirname, "./src/main/resources"),
        "js": path.resolve(__dirname, "./src/main/js"),
      }
    },
    // Vite options tailored for Tauri development and only applied in `tauri dev` or `tauri build`
    // prevent Vite from obscuring rust errors
    clearScreen: false,
    server: {
      port: 9876,
      strictPort: true,
      host: host || false,
      hmr: host
        ? {
          protocol: "ws",
          host,
          port: 9877,
        }
        : undefined,
      watch: {
        // tell Vite to ignore watching `src-tauri`
        ignored: ["**/src-tauri/**"],
      },
    },
    build: {
      outDir: 'dist'
    }
  };
});
