/* Endpoints de la API para las actualizaciones dinámicas */
const API = {
    hechosIrrestrictos: "/api/public/hechos?modo=irrestricto&limit=6",
    hechosCurados:      "/api/public/hechos?modo=curado&limit=6"
};

const $ = (sel, ctx = document) => ctx.querySelector(sel);
const $$ = (sel, ctx = document) => Array.from(ctx.querySelectorAll(sel));

/* --- INTERACTIVIDAD DEL CLIENTE --- */

/* Mobile nav toggle */
const navBtn = $(".nav-toggle");
const nav = $("#menu-principal");
if (navBtn) {
    navBtn.addEventListener("click", () => {
        const open = nav.classList.toggle("open");
        navBtn.setAttribute("aria-expanded", String(open));
        navBtn.setAttribute("aria-label", open ? "Cerrar menú" : "Abrir menú");
    });
}

/* Helper para crear una tarjeta de hecho (usado para la carga dinámica de tabs) */
function cardHecho({ titulo, descripcion, categoria, lugar, fecha }) {
    const fechaFmt = fecha ? new Date(fecha).toLocaleDateString("es-AR") : "—";
    const badge = (label) => `<span class="badge">${label}</span>`;

    return `
    <article class="card" role="listitem">
      <div class="card-header">
        <h3>${titulo}</h3>
      </div>
      <div class="card-meta">
        ${categoria ? badge(categoria) : ""} ${lugar ? badge(lugar) : ""} ${badge(fechaFmt)}
      </div>
      <div class="card-body">
        <p>${descripcion}</p>
      </div>
    </article>`;
}

/* Fetch para obtener datos de la API */
async function getJSON(url) {
    try {
        const r = await fetch(url, { headers: { Accept: "application/json" } });
        if (!r.ok) throw new Error("HTTP " + r.status);
        return await r.json();
    } catch (e) {
        console.error("Error al cargar datos:", e);
        return null; // Devuelve null si falla
    }
}

/* Función para cargar hechos dinámicamente al cambiar de tab */
async function cargarHechos(modo = "irrestricto") {
    const el = $("#hechos-grid");
    if (!el) return;

    el.innerHTML = "<p>Cargando hechos...</p>"; // Feedback para el usuario
    const url = modo === "curado" ? API.hechosCurados : API.hechosIrrestrictos;
    const data = await getJSON(url);

    // Si la API responde y tiene 'items', los renderiza. Si no, muestra un mensaje.
    if (Array.isArray(data?.items) && data.items.length > 0) {
        el.innerHTML = data.items.map(cardHecho).join("");
    } else {
        el.innerHTML = "<p>No se encontraron hechos para esta categoría.</p>";
    }
}

/* Tabs de modo de navegación */
$$(".tab").forEach(btn => {
    btn.addEventListener("click", () => {
        // Si el tab ya está activo, no hace nada
        if (btn.classList.contains("is-active")) return;

        $$(".tab").forEach(b => {
            b.classList.remove("is-active");
            b.setAttribute("aria-selected", "false");
        });
        btn.classList.add("is-active");
        btn.setAttribute("aria-selected", "true");

        // Llama a la función para cargar dinámicamente los nuevos hechos
        cargarHechos(btn.dataset.mode);
    });
});

/* Animaciones de entrada (si las quieres mantener) */
document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".fade-in-left, .fade-in-right, .fade-in-up")
        .forEach((el, i) => {
            el.style.animationDelay = `${i * 0.3}s`;
            el.classList.add("animate");
        });
});