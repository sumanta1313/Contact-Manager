console.log("admin user");

document
  .querySelector("#file") // updated from #image_file_input to #file
  .addEventListener("change", function (event) {
    let file = event.target.files[0];
    let reader = new FileReader();
    reader.onload = function () {
      const preview = document.querySelector("#upload_image_preview");
      preview.setAttribute("src", reader.result);
      preview.classList.remove("hidden"); // show image if it was hidden
    };
    reader.readAsDataURL(file);
  });
