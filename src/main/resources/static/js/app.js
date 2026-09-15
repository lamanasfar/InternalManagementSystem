/* ===== Utility ===== */
function fmtDate(d) {
    if (!d) return '—';
    const p = String(d).split('-');
    return p.length === 3 ? `${p[2]}.${p[1]}.${p[0]}` : d;
}

function todayStr() {
    return new Date().toISOString().slice(0, 10);
}

function toast(msg, type = 'success') {
    const container = document.getElementById('toast-container');
    if (!container) return;
    const el = document.createElement('div');
    el.className = `toast ${type}`;
    el.textContent = msg;
    container.appendChild(el);
    setTimeout(() => el.remove(), 3000);
}

/* ===== Navigation ===== */
document.querySelectorAll('.nav-toggle').forEach(toggle => {
    toggle.addEventListener('click', e => {
        e.preventDefault();
        toggle.closest('.nav-group').classList.toggle('open');
    });
});

document.querySelectorAll('.date-display').forEach(el => {
    el.textContent = fmtDate(todayStr());
});

// Aktiv naviqasiya elementini <body data-page="..."> ilə uyğunlaşdırır
document.addEventListener('DOMContentLoaded', () => {
    const currentPage = document.body.dataset.page;
    if (!currentPage) return;
    document.querySelectorAll('.nav-item[data-page]').forEach(item => {
        if (item.dataset.page === currentPage) {
            item.classList.add('active');
            const group = item.closest('.nav-group');
            if (group) group.classList.add('open');
        }
    });
});

/* ===== Silmə təsdiq modalı ===== */
document.addEventListener('DOMContentLoaded', () => {
    if (document.getElementById('confirm-modal')) return;
    const overlay = document.createElement('div');
    overlay.id = 'confirm-modal';
    overlay.className = 'confirm-modal-overlay';
    overlay.innerHTML = `
    <div class="confirm-modal">
      <div class="confirm-modal-icon"><i class="fa-solid fa-triangle-exclamation"></i></div>
      <p class="confirm-modal-message" id="confirm-modal-message"></p>
      <div class="confirm-modal-actions">
        <button type="button" class="btn btn-cancel" id="confirm-modal-cancel">Ləğv et</button>
        <button type="button" class="btn btn-danger" id="confirm-modal-ok">Sil</button>
      </div>
    </div>`;
    document.body.appendChild(overlay);
});

function confirmDelete(btn, message) {
    const form = btn.closest('form');
    const overlay = document.getElementById('confirm-modal');
    document.getElementById('confirm-modal-message').textContent =
        message || 'Silmək istədiyinizə əminsiniz?';
    overlay.classList.add('open');

    const okBtn = document.getElementById('confirm-modal-ok');
    const cancelBtn = document.getElementById('confirm-modal-cancel');

    const close = () => {
        overlay.classList.remove('open');
        okBtn.removeEventListener('click', onOk);
        cancelBtn.removeEventListener('click', onCancel);
        overlay.removeEventListener('click', onOverlay);
    };
    const onOk = () => { close(); form.submit(); };
    const onCancel = close;
    const onOverlay = e => { if (e.target === overlay) close(); };

    okBtn.addEventListener('click', onOk);
    cancelBtn.addEventListener('click', onCancel);
    overlay.addEventListener('click', onOverlay);
}

/* ===== Cədvəldə sətir üzərində birbaşa redaktə ===== */

// Qələm düyməsi: sətri redaktə rejiminə keçirir
function startEditRow(btn) {
    const row = btn.closest('tr');
    row.querySelectorAll('.view-val').forEach(el => el.classList.add('hidden'));
    row.querySelectorAll('.edit-val').forEach(el => el.classList.remove('hidden'));
    row.querySelector('.view-actions').classList.add('hidden');
    row.querySelector('.edit-actions').classList.remove('hidden');
}

// Qırmızı X: dəyişiklikləri ləğv edir, sətri əvvəlki görünüşə qaytarır
function cancelEditRow(btn) {
    const row = btn.closest('tr');
    row.querySelectorAll('.edit-val').forEach(el => {
        if (el.tagName === 'SELECT') {
            el.value = el.dataset.original || '';
        } else {
            el.value = el.defaultValue;
        }
        el.classList.add('hidden');
    });
    row.querySelectorAll('.view-val').forEach(el => el.classList.remove('hidden'));
    row.querySelector('.edit-actions').classList.add('hidden');
    row.querySelector('.view-actions').classList.remove('hidden');
}

// Yaşıl qalstuk: redaktə olunan dəyərləri gizli formaya köçürüb submit edir
function saveEditRow(btn) {
    const row = btn.closest('tr');
    const id = row.dataset.id;
    const form = document.getElementById('edit-customer-form');
    if (!form || !id) return;

    row.querySelectorAll('.edit-val').forEach(el => {
        const field = el.dataset.field;
        const hidden = form.querySelector(`[name="${field}"]`);
        if (hidden) hidden.value = el.value;
    });

    form.action = '/customers/edit/' + id;
    form.submit();
}