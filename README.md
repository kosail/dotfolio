![dotfolio logo](repo_images/banner.png)

A minimalist, interactive, Windows 11 themed developer portfolio built with [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform).  
Inspired by Mica aesthetics, this project blends creativity with code — aiming to speak louder than words.

📎 Think of it as a personal OS. One that boots into who I am.

---

## 🌼 Features (Planned)

- ⚡️ Static desktop-like UI with interactive elements (icons, windows)
- 📁 Clickable icons that open custom "apps" (CV, a photo of me, projects, etc)
- 📝 Launches with a central notepad window describing the author
- 🧠 Built entirely in Kotlin Multiplatform (WASM target)
- 🎨 Clean, elegant aesthetics with a focus on detail and interactivity


### \--> The project just started. This preview is barebones \<--
![Nighly app logo](repo_images/nightly_appearance.webp)

---

## 🌺 Goal of this project:

The sole goal is to stand out in a sea of traditional portfolios. It's to tell, build an interactive story about myself disguised as an operating system.

Taking this in mind, I focused on implementing only what is needed to tell this story, not to rebuild the actual functionality of an entire OS on Compose.

I consider this minimalist precision is not a limitation, but a design power move. Make it speak clearly, cleanly, and with purpose. Demonstrate creativity, UI/UX sensibility, and Kotlin/Compose skills. That's my goal, and the one of this project.

---

## 🌻 Setup

> Clone and build the project locally.

```bash
git clone https://github.com/kosail/dotfolio.git
cd dotfolio
./gradlew wasmJsBrowserDistribution
```

---

## 🔧 Stack & Resources
### Stack
- **Kotlin** — Main language
- **Compose Multiplatform (WASM)** — UI framework
- **GitHub Pages** — For deployment (expected static hosting)

### Resources
- [Windows 11 icon theme, by yeyushengfan258](https://github.com/yeyushengfan258/Win11-icon-theme/)
- [Notepad Win11 icon, from Wikipedia Commons](https://commons.wikimedia.org/wiki/File:Notepad_Win11.svg)
- [Selawik font, by Microsoft](https://github.com/microsoft/Selawik) (I've had A LOT of issues adding this font to the WASM version of this project, but as I plan to make the effort to make Selawik the default font, I added it)

---

## 💐 Contributing
Contributions are welcome! Feel free to fork the repository and submit pull requests. If you have ideas, suggestions, or bug reports, open an issue on GitHub.

---

[//]: # (## 🎒 What I learned from this project)

[//]: # (---)

## 📜 License
![GPLv3 License logo. Copyright © 2012 Christian Cadena](repo_images/license-logos-by-christian-candena-GNU_GPLv3_License.png)

[GPLv3 (GNU General Public License v3)](LICENSE.txt) – Free to use, modify, and distribute as long as this remains open source, and it is not use for profitable purposes.

GPLv3 Logos:

    Copyright © 2012 Christian Cadena
    Available under the Creative Commons Attribution 3.0 Unported License.


---
> **Note:** dotfolio is a personal learning project and is not affiliated with Microsoft, Windows or any other brand or product.
---
dotfolio Copyright © 2025, kosail
<br>
With love, from Honduras.