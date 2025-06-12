
        // Função para alternar entre as telas
        function showScreen(screenId) {
            // Esconde todas as telas
            document.querySelectorAll('.screen').forEach(screen => {
                screen.classList.remove('active');
            });
            
            // Mostra a tela solicitada
            document.getElementById(screenId).classList.add('active');
            
            // Controla a visibilidade do botão Voltar
            const backButton = document.getElementById('back-button');
            if (screenId === 'home-screen') {
                backButton.classList.add('hidden');
            } else {
                backButton.classList.remove('hidden');
            }
        }
        
        // Atualiza data e hora em tempo real
        function updateDateTime() {
            const now = new Date();
            const date = now.toLocaleDateString('pt-BR');
            const time = now.toLocaleTimeString('pt-BR', {hour: '2-digit', minute:'2-digit'});
            
            document.getElementById('date').textContent = `DATA: ${date}`;
            document.getElementById('time').textContent = `HORÁRIO ${time}`;
        }
        
        // Atualiza a data/hora a cada minuto
        setInterval(updateDateTime, 60000);
        updateDateTime(); // Inicializa
        
        // Configura a data de exemplo fornecida
        document.getElementById('date').textContent = "DATA: 10/06/2025";
        document.getElementById('time').textContent = "HORÁRIO 19:58";
