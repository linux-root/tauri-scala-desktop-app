module.exports = {
  content: [
    './src/main/scala/**/*.scala'
  ],
  theme: {
    extend: {},
  },
  darkMode: 'selector',
  plugins: [
    require('daisyui')
  ],
  daisyui: {
    themes: [
      {
        dark: {
          ...require("daisyui/src/theming/themes")["dark"],
          primary: "#3b82f6",        // blue-500 - professional, enterprise blue
          secondary: "#6b7280",      // gray-500 - neutral secondary
          accent: "#10b981",         // emerald-500 - success accent
          neutral: "#374151",        // gray-700
          "base-100": "#111827",     // gray-900 - clean dark background
          "base-200": "#1f2937",     // gray-800
          "base-300": "#374151",     // gray-700
          info: "#3b82f6",           // blue-500
          success: "#10b981",        // emerald-500
          warning: "#f59e0b",        // amber-500
          error: "#ef4444",          // red-500
        },
      },
      "light"
    ],
    darkTheme: "dark",
    base: true,
    styled: true,
    utils: true,
  },
}

