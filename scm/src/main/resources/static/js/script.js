console.log("script loaded")

// change theme 

let currentTheme = gettheme();
changetheme(currentTheme)
//todo:
function changetheme(){
    // set to web page
    document.querySelector('html').classList.add(currentTheme)
// set a listner to the button
const changetheme=document.querySelector('#theme_change');
changetheme.addEventListener("click",(event) => {
   const oldtheme = currentTheme;
    if (currentTheme === "dark"){
        currentTheme="light";
    }else{
currentTheme="dark";
    }

// remove the old theme 
document.querySelector('html').classList.remove(oldtheme);
    // updating to local storage
    settheme(currentTheme);
    document.querySelector('html').classList.add(currentTheme);
});
}
    // set theeme to local storage
function settheme(theme){
    localStorage.setItem("theme",theme);
}
// get theme from local storage
function gettheme(){
    let theme=localStorage.getItem("theme");
    if (theme) return theme;
    else return "light";
}

