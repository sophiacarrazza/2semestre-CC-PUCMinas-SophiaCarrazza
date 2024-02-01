
class Converter {
    constructor() {
      this.form = document.querySelector('form');
      this.numero = document.getElementById("num");
      this.baseAtual = document.getElementById("baseAtual");
      this.baseFutura = document.getElementById("baseFutura");
      this.divErro = document.getElementById("erro");
  
      this.form.addEventListener('submit', this.handleSubmit.bind(this));
      this.numero.addEventListener('keypress', this.validateInput.bind(this));
    }
  
    handleSubmit(event) {
      event.preventDefault();
      let number = this.convertBase(this.numero.value, this.baseAtual.value, this.baseFutura.value);
      this.displayResult(number);
    }
  
    validateInput(event) {
      this.divErro.innerText = '';
      const keyCode = event.key;
  
      if (!(keyCode === 'Backspace' || /^[A-Z0-9]$/.test(keyCode))) {
        this.divErro.innerText = "Por favor, digite numeros ou letras maiusculas";
        console.log(this.divErro.innerText);
        event.preventDefault();
      }
    }
  
    convertBase(number, baseFrom, baseTo) {
      return parseInt(number, baseFrom).toString(baseTo).toUpperCase();
    }
  
    displayResult(result) {
        const resultElement = document.getElementById("result");
        resultElement.innerText = result;
      }
  }
  
  new Converter();