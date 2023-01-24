import {defineConfig} from 'vite'
import react from '@vitejs/plugin-react'
import resolve from '@rollup/plugin-node-resolve'
import svgrPlugin from 'vite-plugin-svgr'
import tsConfigPaths from 'vite-tsconfig-paths'

export default defineConfig({
  define: {
    'process.env': process.env,
  },
  build: {
    outDir: 'build',
    assetsDir: 'static',
    commonjsOptions: {
      transformMixedEsModules: false
    },
    chunkSizeWarningLimit: 1024
  },
  plugins: [
    tsConfigPaths(),
    resolve({
      extensions: ['.js', '.ts']
    }),
    react(),
    svgrPlugin({
      svgrOptions: {
        icon: true
      }
    })],
  resolve: {
    alias: {
      inherits: './inherits.js',
    },
  }
})
