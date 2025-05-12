console.log("script loaded");

// Get theme from local storage or default to light
let currentTheme = getTheme();
applyTheme(currentTheme);

// Function to apply the current theme
function applyTheme(theme) {
    document.querySelector('html').classList.add(theme);
}

// Listen for theme toggle button click
const changeThemeBtn = document.querySelector('#theme_change');
if (changeThemeBtn) {
    changeThemeBtn.addEventListener("click", () => {
        const oldTheme = currentTheme;
        currentTheme = currentTheme === "dark" ? "light" : "dark";

        // Update the HTML tag
        document.querySelector('html').classList.remove(oldTheme);
        document.querySelector('html').classList.add(currentTheme);

        // Save to local storage
        setTheme(currentTheme);
    });
}

// Save theme to local storage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

// Get theme from local storage
function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}
