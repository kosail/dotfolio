(async function () {
    const app = document.body;
    const loading = document.getElementById("container");

    const script = document.createElement("script");
    script.src = "composeApp.js";
    script.type = "application/javascript";

    script.onload = () => {
        loading.remove();
    };

    app.appendChild(script);
})();