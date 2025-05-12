

/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [],
  theme: {
    extend: { animation: {
      shine: 'shine 1.5s ease-out infinite',
    },
    keyframes: {
      shine: {
        '0%': { left: '-100px' },
        '60%, 100%': { left: '100%' },
      },
    }, },
  },
  plugins: [
    require('flowbite/plugin')
  ],
}

