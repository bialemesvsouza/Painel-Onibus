const onibusSimulados = [
    // Linha 100 - 5 ônibus
    { codigo: "1001", baseLat: -23.5505, baseLng: -46.6333 },
    { codigo: "1002", baseLat: -23.5510, baseLng: -46.6340 },
    { codigo: "1003", baseLat: -23.5520, baseLng: -46.6325 },
    { codigo: "1004", baseLat: -23.5495, baseLng: -46.6310 },
    { codigo: "1005", baseLat: -23.5530, baseLng: -46.6345 },

    // Linha 200 - 5 ônibus
    { codigo: "2001", baseLat: -23.5550, baseLng: -46.6400 },
    { codigo: "2002", baseLat: -23.5560, baseLng: -46.6410 },
    { codigo: "2003", baseLat: -23.5540, baseLng: -46.6390 },
    { codigo: "2004", baseLat: -23.5570, baseLng: -46.6420 },
    { codigo: "2005", baseLat: -23.5535, baseLng: -46.6380 },

    // Linha 300 - 5 ônibus
    { codigo: "3001", baseLat: -23.5505, baseLng: -46.6333 },
    { codigo: "3002", baseLat: -23.5600, baseLng: -46.6500 },
    { codigo: "3003", baseLat: -23.5580, baseLng: -46.6480 },
    { codigo: "3004", baseLat: -23.5590, baseLng: -46.6470 },
    { codigo: "3005", baseLat: -23.5610, baseLng: -46.6510 }
];

function gerarCoordenadaAleatoria(base, variacao = 0.001) {
    return base + (Math.random() * variacao * 2 - variacao);
}

function simularEnvioDePosicoes() {
    onibusSimulados.forEach(async (bus) => {
        const latitude = gerarCoordenadaAleatoria(bus.baseLat);
        const longitude = gerarCoordenadaAleatoria(bus.baseLng);

        const payload = {
            codigoOnibus: bus.codigo,
            latitude,
            longitude
        };

        try {
            const res = await fetch("http://localhost:8080/api/rastreamento/atualizar", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(payload)
            });

            if (res.ok) {
                console.log(`✅ Enviado para ${bus.codigo}: (${latitude}, ${longitude})`);
            } else {
                console.warn(`❌ Falha para ${bus.codigo}`);
            }
        } catch (error) {
            console.error(`❌ Erro para ${bus.codigo}:`, error);
        }
    });
}

setInterval(simularEnvioDePosicoes, 15000);
simularEnvioDePosicoes();
