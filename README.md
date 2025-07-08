![dotfolio logo](repo_images/banner.webp)

A minimalist, interactive, Windows 11 themed developer portfolio built with [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform).  
Inspired by Mica aesthetics, this project blends creativity with code — aiming to speak louder than words.

📎 Think of it as a personal OS. One that boots into who I am.

---

## 🌼 Features

- ⚡️ Static desktop-like UI with interactive elements (icons, windows)
- 📁 Clickable icons that open custom "apps" (CV, a photo of me, projects, etc.)
- 📝 Launches with a central notepad window describing the author
- 🧠 Built entirely in Kotlin Multiplatform (WASM target)
- 🎨 Clean, elegant aesthetics with a focus on detail and interactivity

![Nighly app logo](repo_images/nightly_appearance.webp)

---
## 🪻 Development Stage
### Main
- ✅ Desktop Environment:
    - ❌ Z Index for apps
    - ❌ Selawik Font on Web target

    

### Apps
- ✅ Notepad
- ✅ Settings
- ✅ Photos
- ✅ Media Player
- ❌ Web Browser
- ❌ File Manager
- ❌ PDF file download

### Bugs

<details>
<summary>Show/Hide</summary>

1. ### Windows are going under the shortcut icons, which doesn't make sense.

    Windows should be OVER any other element
    This might mean that I will have to implement a Z index... bro
    I thought I could have made it without implementing that.
    I already tried to switch positions between DesktopEnvironment and DesktopShortcuts, but it's not that easy it seems.

2. ### When we open a second (or more) window and we close the first one, the second window moves into the position where the first one was.
    Which doesn't make sense! Windows should have their position independent to other windows.
    I have no idea whatzupp with this and what it is causing it. I'll check on that later on when I finish to code all of the "apps" inside dotfolio

3. ### Media player works seamlessly on web target, but in Desktop it has a bug in which if the audio is paused, then it cannot be played again

</details>

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

#### Wallpapers
- [Anime wallpaper, by Jensen Art Co. on Pixabay](https://pixabay.com/users/jensenartofficial-31380959/)
- [Cat wallpaper, by Zeke Tucker on Unsplash](https://unsplash.com/@zeketucker)
- [Honduras wallpaper 1, by Hector Emilio Gonzalez on Unsplash](https://unsplash.com/@hectoremilio)
- [Honduras wallpaper 2, by Ramon Flores on Unsplash](https://unsplash.com/@ramonantoniof)


---

## 💐 Contributing
Contributions are welcome! Feel free to fork the repository and submit pull requests. If you have ideas, suggestions, or bug reports, open an issue on GitHub.

---

[//]: # (## 🎒 What I learned from this project)

[//]: # (---)

## 📜 License
![GPLv3 License logo. Copyright © 2012 Christian Cadena](repo_images/license-logos-by-christian-candena-GNU_GPLv3_License.webp)

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