function buscarCliente() {
    const nit = document.getElementById('nitInput').value;
    axios.get(`/api/facturas/info/${nit}`)
      .then(res => {
        const data = res.data;
        const cliente = data.nit;
        const factura = data.facturaProxima;
  
        document.getElementById('infoCliente').innerHTML = `
          <p><strong>Nombre:</strong> ${cliente.nitNom}</p>
          <p><strong>Cupo:</strong> $${cliente.nitCup}</p>
          <p><strong>Plazo:</strong> ${cliente.nitPla} días</p>
          <p><strong>Cartera:</strong> $${cliente.nitCar}</p>
          <p><strong>Disponible:</strong> $${cliente.nitDis}</p>
        `;
  
        if (factura) {
          document.getElementById('infoFactura').innerHTML = `
            <p><strong>Número:</strong> ${factura.facNum}</p>
            <p><strong>Fecha creación:</strong> ${factura.facFec}</p>
            <p><strong>Fecha vencimiento:</strong> ${factura.facFecVen}</p>
          `;
        } else {
          document.getElementById('infoFactura').innerText = 'No hay factura próxima registrada.';
        }
      })
      .catch(() => {
        alert('Cliente no encontrado');
      });
  }
  
  function buscarArticulo() {
    const cod = document.getElementById('codigoArticulo').value;
    axios.get(`/api/articulos/${cod}`)
      .then(res => {
        const art = res.data;
        document.getElementById('infoArticulo').innerHTML = `
          <p><strong>Nombre:</strong> ${art.artNom}</p>
          <p><strong>Laboratorio:</strong> ${art.artLab}</p>
          <p><strong>Saldo:</strong> ${art.artSal}</p>
        `;
      })
      .catch(() => {
        alert('Artículo no encontrado');
      });
  }
  
  function toggleCampos() {
    const naturaleza = document.getElementById('naturaleza').value;
    const costos = document.getElementById('costos');
    const precioVenta = document.getElementById('precioVenta');
  
    if (naturaleza === 'entrada') {
      costos.removeAttribute('disabled');
      precioVenta.setAttribute('disabled', 'true');
    } else {
      costos.setAttribute('disabled', 'true');
      precioVenta.removeAttribute('disabled');
    }
  }
  
  function guardarMovimiento(e) {
    e.preventDefault();
  
    const nit = document.getElementById('nitInput').value;
    const codArt = document.getElementById('codigoArticulo').value;
    const naturaleza = document.getElementById('naturaleza').value;
    const unidades = parseInt(document.getElementById('unidades').value);
    const costos = parseFloat(document.getElementById('costos').value);
    const precioVenta = parseFloat(document.getElementById('precioVenta').value);
  
    if (!nit || !codArt || !unidades || (naturaleza === 'entrada' && !costos) || (naturaleza === 'salida' && !precioVenta)) {
      alert('Completa todos los campos requeridos');
      return;
    }
  
    if (naturaleza === 'salida' && precioVenta <= costos) {
      alert('El precio de venta debe ser mayor al costo unitario.');
      return;
    }
  
    const payload = {
      nit: nit,
      articulo: codArt,
      naturaleza: naturaleza,
      unidades: unidades,
      costos: naturaleza === 'entrada' ? costos : null,
      precioVenta: naturaleza === 'salida' ? precioVenta : null
    };
  
    axios.post('/api/kardex', payload)
      .then(res => {
        alert('Movimiento registrado con éxito');
        cargarKardex(); 
      })
      .catch(() => {
        alert('Error al guardar el movimiento');
      });
  }
  
  function cargarKardex() {
    const codArt = document.getElementById('codigoArticulo').value;
    axios.get(`/api/kardex/${codArt}`)
      .then(res => {
        const tbody = document.querySelector('#tablaKardex tbody');
        tbody.innerHTML = '';
        let totalCostos = 0;
        let totalVenta = 0;
  
        res.data.forEach(entry => {
          tbody.innerHTML += `
            <tr>
              <td>${entry.articulo}</td>
              <td>${entry.entrada || '-'}</td>
              <td>${entry.salida || '-'}</td>
              <td>${entry.saldo}</td>
              <td>${entry.costoUnitario || '-'}</td>
              <td>${entry.precioVenta || '-'}</td>
              <td>${entry.totalCostos || 0}</td>
              <td>${entry.totalVenta || 0}</td>
              <td>${entry.fechaVencimiento || '-'}</td>
            </tr>
          `;
          totalCostos += entry.totalCostos || 0;
          totalVenta += entry.totalVenta || 0;
        });
  
        document.getElementById('totalCostos').textContent = totalCostos.toFixed(2);
        document.getElementById('totalVenta').textContent = totalVenta.toFixed(2);
      });
  }
  