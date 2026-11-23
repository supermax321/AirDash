<div align="center">

# ⚡ AirDash

<p align="center">
  <a href="https://minecraft.wiki/">
    <img src="https://img.shields.io/badge/Minecraft-1.21.1%2B-2EA44F?style=for-the-badge&logo=minecraft&logoColor=white" alt="Minecraft Version" />
  </a>
  <a href="https://fabricmc.net/">
    <img src="https://img.shields.io/badge/Loader-Fabric-1F8B4D?style=for-the-badge&logo=fabric&logoColor=white" alt="Fabric Loader" />
  </a>
  <a href="https://www.oracle.com/java/">
    <img src="https://img.shields.io/badge/Java-21-007396?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21" />
  </a>
  <a href="https://opensource.org/licenses/MIT">
    <img src="https://img.shields.io/badge/License-MIT-blue?style=for-the-badge" alt="License" />
  </a>
</p>

**Расширенная версия (форк) Fabric-мода AirDash для Minecraft 1.21.11 с поддержкой 3D-рывков, бесконечных прыжков и гибкой настройки.**

---

</div>

## 🚀 Особенности

* **3D-рывок по взгляду:** Позволяет направлять рывок по прицелу (вверх, вниз или под углом).
* **Бесконечные прыжки:** Опциональная возможность отталкиваться от воздуха неограниченное число раз.
* **Управление и GUI:** Горячая клавиша включения/выключения (`R`), настраиваемая кнопка рывка и индикатор над хотбаром.
* **Снятие лимитов:** Убраны ограничения на силу рывка/прыжка и количество частиц (с защитой от зависаний).
* **Исправления:** Сохранение настроек при перезапуске и отсутствие спам-сообщений в чате.

---

## 🛠️ Требования

Для работы мода необходимы следующие зависимости:

| Зависимость | Версия | Обязательно |
| :--- | :--- | :---: |
| **[Fabric Loader](https://fabricmc.net/)** | `>=0.15.0` | ✅ |
| **[Fabric API](https://modrinth.com/mod/fabric-api)** | Совместимая с версией игры | ✅ |
| **[Cloth Config API](https://modrinth.com/mod/cloth-config)** | `v15+` | ✅ |
| **[Mod Menu](https://modrinth.com/mod/modmenu)** | Любая | 💡 (для GUI настроек) |

---

## ⚙️ Конфигурация

Все параметры редактируются прямо в игре через **Mod Menu** или в файле `.minecraft/config/airdash.json`:

* `enabled` — Включение мода (по умолч. `true`)
* `lookDirectionDash` — 3D-рывок по взгляду (`false`)
* `infiniteAirJumps` — Бесконечные рывки в воздухе (`false`)
* `forwardPower` — Сила ускорения вперёд (`0.8`)
* `jumpPower` — Сила подброса вверх (`0.7`)
* `particleType` — Тип партиклов (`FLAME`, `SOUL_FIRE_FLAME`)
* `particleCount` — Кол-во частиц (`25`)
* `disableFallDamage` — Отключение урона от падения (`true`)

---

## 🔗 Ссылки и оригинальный проект

* 🌐 **Сайт разработчика:** [kotmir.tech](https://kotmir.tech)
* 💬 **Discord сообщество:** [discord.gg/kotmiks](https://discord.gg/kotmiks)
* 📦 **Modrinth:** [AirDash on Modrinth](https://modrinth.com/mod/airdash)
