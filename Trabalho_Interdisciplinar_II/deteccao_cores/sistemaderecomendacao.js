

axios.get("http://localhost:6789/usuario/list")
    .then(response => {
      array = response.data;
var cor = array.cor;
});