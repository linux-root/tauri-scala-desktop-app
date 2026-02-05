module.exports = {
  content: [
    './src/main/scala/**/*.scala'
  ],
  safelist: [
    // Dynamic classes used in Scala string interpolation
    'text-primary',
    'text-secondary',
    'text-accent',
    'text-success',
    'text-info',
    'text-warning',
    'text-error',
    'bg-primary',
    'bg-secondary',
    'bg-accent',
    'bg-success',
    'bg-info',
    'bg-warning',
    'bg-error',
  ],
  theme: {
    extend: {
      colors: {
        baltic:  { DEFAULT: '#22577a', 50: '#e8f4fa', 100: '#c5e3f0', 500: '#22577a', 700: '#183d56', 900: '#0e2233' },
        teal:    { DEFAULT: '#38a3a5', 50: '#eaf7f7', 100: '#c8ebe9', 500: '#38a3a5', 700: '#287475', 900: '#184546' },
        emerald: { DEFAULT: '#57cc99', 50: '#edfbf3', 100: '#d0f4e1', 500: '#57cc99', 700: '#3d9e73', 900: '#24704d' },
        lgreen:  { DEFAULT: '#80ed99', 50: '#f0fdf4', 100: '#dcfce7', 500: '#80ed99', 700: '#5cc47a', 900: '#389b5b' },
        tea:     { DEFAULT: '#c7f9cc', 50: '#f7fef8', 100: '#eafded', 500: '#c7f9cc', 700: '#96d89e', 900: '#65b770' },
      },
    },
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
          primary: "#22577a",
          "primary-content": "#e0f2f1",
          secondary: "#38a3a5",
          "secondary-content": "#e0f2f1",
          accent: "#57cc99",
          "accent-content": "#0b1a23",
          neutral: "#193744",
          "neutral-content": "#e0f2f1",
          "base-100": "#0b1a23",
          "base-200": "#112832",
          "base-300": "#193744",
          "base-content": "#e0f2f1",
          info: "#38a3a5",
          "info-content": "#e0f2f1",
          success: "#57cc99",
          "success-content": "#0b1a23",
          warning: "#f59e0b",
          "warning-content": "#0b1a23",
          error: "#ef4444",
          "error-content": "#e0f2f1",
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
