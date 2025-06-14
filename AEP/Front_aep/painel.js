const API_BASE_URL = 'http://localhost:8080/api';
let linhasData = [];

document.addEventListener('DOMContentLoaded', () => {
    loadLinhas();
    setupDateTime();
    setupButtonListeners();
});

async function loadLinhas() {
    try {
        const res = await fetch(`${API_BASE_URL}/linhas`);
        linhasData = await res.json();
        createLineButtons(linhasData);
    } catch (err) {
        console.error('Erro ao carregar linhas:', err);
        showError('Erro ao carregar linhas do sistema.');
    }
}

function createLineButtons(linhas) {
    const container = document.querySelector('.buttons-container');
    container.innerHTML = '';

    linhas.forEach(linha => {
        const button = document.createElement('button');
        const cor = getLineColorClass(linha.codigo);

        button.className = `line-button ${cor}`;
        button.innerHTML = `<i class="fas fa-bus"></i> ${linha.nome.toUpperCase()}`;
        button.onclick = () => loadPrevisaoPorLinha(linha);
        container.appendChild(button);
    });
}

async function loadPrevisaoPorLinha(linha) {
    const paradas = linha.paradas || [];
    const telaId = `${getLineColorClass(linha.codigo)}-line`;
    const tela = document.getElementById(telaId);
    const container = tela.querySelector('.schedule-container');

    container.innerHTML = ''; // limpa antes de renderizar

    // Desabilita os botões para evitar cliques enquanto carrega
    const buttons = document.querySelectorAll('.line-button');
    buttons.forEach(btn => btn.disabled = true);

    try {
        for (const parada of paradas) {
            const rota = `${parada.nome.toUpperCase()} - ${linha.codigo}`;
            try {
                const res = await fetch(`${API_BASE_URL}/paineis/${parada.codigo}/previsao`);
                const json = await res.json();
                const previsoes = json.previsoes || [];

                const div = document.createElement('div');
                div.className = 'route';

                if (previsoes.length === 0) {
                    div.innerHTML = `
                        <div class="route-name">
                            <i class="fas fa-map-marker-alt"></i> ${rota}
                        </div>
                        <div class="schedule">Nenhuma previsão disponível</div>
                    `;
                } else {
                    div.innerHTML = `
                        <div class="route-name">
                            <i class="fas fa-map-marker-alt"></i> ${rota}
                        </div>
                        <div class="schedule">HORÁRIO</div>
                        <div class="times">
                            ${previsoes.map(p => `
                                <div class="time ${getLineColorClass(linha.codigo)}">
                                    ${calcularHorarioChegada(p.tempoEstimadoMinutos)}
                                </div>
                            `).join('')}
                        </div>
                    `;
                }

                container.appendChild(div);
            } catch (err) {
                console.warn(`Erro ao buscar previsão da parada ${parada.codigo}:`, err);
            }
        }
    } finally {
        // Reabilita os botões ao final
        buttons.forEach(btn => btn.disabled = false);
    }

    showScreen(telaId);
}


function calcularHorarioChegada(minutos) {
    const agora = new Date();
    agora.setMinutes(agora.getMinutes() + minutos);
    return agora.toTimeString().substring(0, 5); // HH:MM
}

function getLineColorClass(codigo) {
    if (codigo.includes('AZUL') || codigo.includes('100')) return 'blue';
    if (codigo.includes('AMARELA') || codigo.includes('200')) return 'yellow';
    if (codigo.includes('VERMELHA') || codigo.includes('300')) return 'red';
    return 'blue';
}

function showScreen(screenId) {
    document.querySelectorAll('.screen').forEach(screen => {
        screen.classList.remove('active');
    });

    document.getElementById(screenId)?.classList.add('active');

    const backButton = document.getElementById('back-button');
    if (screenId === 'home-screen') {
        backButton.classList.add('hidden');
    } else {
        backButton.classList.remove('hidden');
    }
}

function showError(message) {
    alert(message);
}

function setupDateTime() {
    function updateDateTime() {
        const now = new Date();
        const date = now.toLocaleDateString('pt-BR');
        const time = now.toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' });

        document.getElementById('date').textContent = `DATA: ${date}`;
        document.getElementById('time').textContent = `HORÁRIO ${time}`;
    }

    updateDateTime();
    setInterval(updateDateTime, 60000);
}

function setupButtonListeners() {
    document.getElementById('back-button').addEventListener('click', () => {
        showScreen('home-screen');
    });
}
