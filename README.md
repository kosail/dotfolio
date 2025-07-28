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
- 🌐 Runs fully client-side, no backend, no trackers.
- 🤳 Render different versions of dotfolio depending on if it's a desktop, a tablet or a mobile phone.

> Dotfolio is structured like an operating system, but powered by storytelling. Just check it out:


![Showdown of some apps](repo_images/nightly_appearance.webp)
![Web browser app opened up](repo_images/nightly_appearance_2.webp)
![About project](repo_images/nightly_appearance_3.webp)


<details>
<summary>On desktop it loads the full version. On tablets, it loads just the content of dotfolio's web browser (the blog of myself), and on mobile it shows a nice message telling mobile is not currently supported</summary>

![On tablet preview](repo_images/ontablet_appearance.webp)

</details>


---
## 🪻 Development Stage
### Main
- ✅ Desktop Environment:
    - ✅ Z Index for apps (Not an actual z index, since I just used Compose's rendering order...)
    - 🌱 Windows minimize capabilities
    - ❌ Windows dynamic size at launch based on the viewport

### Apps
- ✅ Notepad
- ✅ Settings
- ✅ Photos
- ✅ Media Player (Missing real audios)
- ✅ Web Browser
- ✅ File Manager
- ✅ PDF file download (Missing real PDF)

### Bugs

<details>
<summary>Show/Hide</summary>

1. ### Media player works seamlessly on web target, but in Desktop it has a bug in which if the audio is paused, then it cannot be played again.
    Although, it's okay. This project focuses on web target, and I use desktop implementation just for its hot reload capabilities, which really makes my life easier when quickly prototyping or fast debugging.
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
- [Windows 11 background light, from 4kwallpapers.com](https://4kwallpapers.com/abstract/windows-11-blue-stock-white-background-light-official-5616.html)
- [Windows 11 background dark, from 4kwallpapers.com](https://4kwallpapers.com/abstract/windows-11-dark-mode-blue-stock-official-5630.html)
- [Anime wallpaper, by Jensen Art Co. on Pixabay](https://pixabay.com/users/jensenartofficial-31380959/)
- [Cat wallpaper, by Zeke Tucker on Unsplash](https://unsplash.com/@zeketucker)
- [Honduras wallpaper 1, by Hector Emilio Gonzalez on Unsplash](https://unsplash.com/@hectoremilio)
- [Honduras wallpaper 2, by Ramon Flores on Unsplash](https://unsplash.com/@ramonantoniof)
- [Purple cat photo, by Adam Gonzales on Unsplash](https://unsplash.com/@adamgonzales)
- [Gnome screenshot, from Blur my shell GitHub repo, by aunetx](https://github.com/aunetx/blur-my-shell)
- Screenshot from the game "Flowers, le volume sur le printemps." Innocent Grey made this wonderful visual novel, and it's available on GOG for purchase.
- [Amberol music player screenshot, from FOSTips](https://fostips.com/amberol-music-player-beautiful-design/)
- [Windows 11 screenshot, from linkvegas12 on DevianArt](https://www.deviantart.com/linkvegas12/art/Fluent-Start-Menu-Style-For-Windows-11-1137827427)

- [Java logo, from Wikimedia Commons](https://commons.wikimedia.org/wiki/File:Duke_%28Java_mascot%29_waving.svg)
- [Kotlin logo, from Wikimedia Commons](https://commons.wikimedia.org/wiki/File:Kotlin_Icon.svg)
- [Compose logo, from Compose official repo on GitHub](https://github.com/JetBrains/compose-multiplatform/blob/master/artwork/compose-logo.svg)
- [C# logo, from Wikimedia Commons](https://commons.wikimedia.org/wiki/File:Logo_C_sharp.svg)
- [HTML logo, from Wikimedia Commons](https://commons.wikimedia.org/wiki/File:HTML5_Badge.svg)
- [CSS logo, from Wikimedia Commons](https://commons.wikimedia.org/wiki/File:Official_CSS_Logo.svg)
- [JS logo, from Wikimedia Commons](https://commons.wikimedia.org/wiki/File:JavaScript-logo.png)
- [Oracle logo, from Wikimedia Commons](https://commons.wikimedia.org/wiki/File:Oracle_logo.svg)
- [Docker logo, from Wikimedia Commons](https://commons.wikimedia.org/wiki/File:Docker_(container_engine)_logo_(cropped).png)
- [Linux logo, from Wikimedia Commons](https://commons.wikimedia.org/wiki/File:TuxFlat.svg)
- [WordPress logo, from Wikimedia Commons](https://commons.wikimedia.org/wiki/File:WordPress_blue_logo.svg)

#### -> Fonts
- Main font: [Selawik font, from Microsoft](https://github.com/microsoft/Selawik)
- Japanese font: [M Plus 1p, from Google Fonts](https://fonts.google.com/specimen/M+PLUS+1p)

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