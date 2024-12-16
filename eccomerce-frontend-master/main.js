import './style.css';

// URL da API
const apiURL = 'http://localhost:8080/produto';

// Função para buscar os produtos da API
export async function fetchPhotos() {
  try {
    const sortOrder = document.getElementById('sort-options').value;
    const searchQuery = document.getElementById('search-bar').value;

    let apiUrl = `${apiURL}?order=${sortOrder}`;
    if (searchQuery) {
      apiUrl += `&filter=${encodeURIComponent(searchQuery)}`;
    }

    const response = await fetch(apiUrl);
    if (!response.ok) throw new Error('Erro na requisição');

    const photos = await response.json();
    displayPhotos(photos);
  } catch (error) {
    console.error('Erro ao carregar os produtos:', error);
  }
}

// Função para exibir os produtos na galeria
function displayPhotos(photos) {
  const gallery = document.getElementById('gallery');
  const totalPhotosElement = document.getElementById('total-photos');
  gallery.innerHTML = '';

  totalPhotosElement.textContent = photos.length;

  photos.forEach(produto => {
    const photoContainer = document.createElement('div');
    photoContainer.classList.add('photo-item');

    const imgElement = document.createElement('img');
    imgElement.src = produto.imagemURL;
    imgElement.alt = produto.descricao;

    const titleElement = document.createElement('h3');
    titleElement.textContent = produto.nome;

    const descriptionElement = document.createElement('p');
    descriptionElement.textContent = produto.descricao;

    const priceElement = document.createElement('p');
    priceElement.textContent = `R$ ${produto.preco.toFixed(2)}`;

    photoContainer.append(imgElement, titleElement, descriptionElement, priceElement);
    gallery.appendChild(photoContainer);
  });
}

// Adiciona os eventos
document.getElementById('search-bar').addEventListener('input', fetchPhotos);
document.getElementById('sort-options').addEventListener('change', fetchPhotos);

// Chama a função ao carregar a página
fetchPhotos();
