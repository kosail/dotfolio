![dotfolio logo](repo_images/banner.webp)

Dotfolio is not just a portfolio — it’s a narrative, a space, an emotional experience disguised as an operating system.

Inspired by Mica aesthetics, this project blends creativity with code — aiming to speak louder than words.

🧭 Built entirely with [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform) — using the WebAssembly + JS target.

---

## 🌼 Features

- 💻 A static desktop environment that looks and feels like Windows 11 — but minimal, soft, and handcrafted
- 🖱️ Fully interactive: a live clock, animated shortcuts, and a taskbar that reacts to opened apps
- 🗂️ Clickable desktop icons launch simulated apps like Notepad, Settings, a Media Player, Web Browser, and my CV
- 🌗 Seamless light and dark mode, and wallpaper customization option included
- 🧠 Each “app” is not just aesthetic — it tells a part of the story: my skills, my personality, my design sense
- 🌐 Runs fully client-side, no backend, no trackers

> Dotfolio is structured like an operating system, but powered by storytelling. Just check it out:


![Showdown of some apps](repo_images/nightly_appearance.webp)
![Web browser app opened up](repo_images/nightly_appearance_3.webp)
![About project](repo_images/nightly_appearance_2.webp)

---
## 🪻 Development Stage
### Main
- ✅ Desktop Environment:
    - ❌ Z Index for apps 
    - ❌ Windows minimize capabilities

### Apps
- ✅ Notepad
- ✅ Settings
- ✅ Photos
- ✅ Media Player
- 🌱 Web Browser
- ❌ File Manager
- ✅ PDF file download

### Bugs

<details>
<summary>Show/Hide</summary>

1. ### Windows are going under the shortcut icons, which doesn't make sense.

    Windows should be OVER any other element.

    This might mean that I will have to implement a Z index... bro
    I thought I could have made it without implementing that.

    I already tried to switch positions between DesktopEnvironment and DesktopShortcuts, but it's not that easy it seems.

2. ### When we open a second (or more) window, and we close the first one, the second window moves into the position where the first one was.
    Which doesn't make sense! Windows should have their position independent of other windows.

    I have no idea whatzupp with this and what it is causing it. I'll check on that later on when I finish coding all the "apps" inside dotfolio

3. ### Media player works seamlessly on web target, but in Desktop it has a bug in which if the audio is paused, then it cannot be played again.
    Although, it's okay. This project focuses on web target, and I use desktop implementation just for its hot reload capabilities, which really makes my life easier when quickly prototyping or fast debugging.

</details>


### Dev notes
<details>
<summary>Show/Hide</summary>

### ~~I may end up dropping the file manager app, as the web browser app became extremely efficient in its tasks.~~
   I decided to drop the original idea of the file manager. It will no longer showcase my projects, but instead just serve as a "tech stack" or something like that. Simple, showing only icons.

</details>

---

## 🌺 Goal of this project:

The sole goal is to stand out in a sea of traditional portfolios. It's to tell, build an interactive story about myself.

Taking this in mind, I focused on implementing only what is needed to tell this story, not to rebuild the actual functionality of an entire OS on Compose.

I consider this minimalist precision is not a limitation, but a design power move. Make it speak clearly, cleanly, and with purpose. That's my goal, and the one of this project.

---

## 🌻 Setup

- Clone and build the project locally.

```bash
git clone https://github.com/kosail/dotfolio.git
cd dotfolio
./gradlew wasmJsBrowserDistribution
```

---

## 🔧 Stack & Resources
### Stack
- **Kotlin** — Main language
- **Compose Multiplatform (WASM/JS)** — UI framework
- **GitHub Pages** — For deployment (expected static hosting)

### Resources

#### -> Icons
- [Windows 11 icon theme, by yeyushengfan258](https://github.com/yeyushengfan258/Win11-icon-theme/)
- [Notepad Win11 icon, from Wikipedia Commons](https://commons.wikimedia.org/wiki/File:Notepad_Win11.svg)
- [Blanket app icon, by Rafael Mardojai](https://github.com/rafaelmardojai/blanket)

#### -> Wallpapers and images
- [Anime wallpaper, by Jensen Art Co. on Pixabay](https://pixabay.com/users/jensenartofficial-31380959/)
- [Cat wallpaper, by Zeke Tucker on Unsplash](https://unsplash.com/@zeketucker)
- [Honduras wallpaper 1, by Hector Emilio Gonzalez on Unsplash](https://unsplash.com/@hectoremilio)
- [Honduras wallpaper 2, by Ramon Flores on Unsplash](https://unsplash.com/@ramonantoniof)
- [Purple cat photo, by Adam Gonzales on Unsplash](https://unsplash.com/@adamgonzales)

#### -> Fonts
- Main font: [Selawik font, from Microsoft](https://github.com/microsoft/Selawik)
- Japanese font: [M Plus 1p, from Google Fonts](https://fonts.google.com/specimen/M+PLUS+1p)
- Emoji font: [Noto Color Emoji, from Google Fonts](https://fonts.google.com/noto/specimen/Noto+Color+Emoji)

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