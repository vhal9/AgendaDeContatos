import axios from 'axios'

export const ApiCepService = axios.create({
  baseURL: 'https://viacep.com.br/ws/' 
})
