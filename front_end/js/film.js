document.getElementById('addSpiceForm').addEventListener('submit', function(event) {
  event.preventDefault();

  const formData = new FormData();
  const spice = {
    title: document.getElementById('title').value,
    description: document.getElementById('description').value,
    genre: document.getElementById('genre').value,
    team: document.getElementById('team').value,
    durationMinutes: document.getElementById('durationMinutes').value,
    releaseDate: document.getElementById('releaseDate').value,
    language: document.getElementById('language').value,
    cast: document.getElementById('cast').value,
    imageUrl: document.getElementById('imageUrl').value,
   trailerUrl: document.getElementById('trailerUrl').value
  };
  formData.append('spice', JSON.stringify(spice));

  const spiceImageFile = document.getElementById('imageUrl');
  if (spiceImageFile && spiceImageFile.files.length > 0) {
    formData.append('file', spiceImageFile.files[0]);
  } else {
    console.error('No image file selected');
    return;
  }

  fetch('http://localhost:8080/api/v1/film/save', {
    method: 'POST',
    headers: {
      "Authorization": "Bearer " + localStorage.getItem("token")
    },
    body: formData
  })
    .then(response => {
      if (!response.ok) {
        alert("film saved successfully")
        getAllCustomer();
        return response.json().then(errorData => {
          throw new Error(JSON.stringify(errorData));
        });
      }
      return response.json();
    })
    .then(data => {

      console.log('film saved successfully:', data);
      getAllCustomer();
    })
    .catch(error => {
      console.error('Error:', error);
      try {
        const errorData = JSON.parse(error.message);
        console.log('Response data:', errorData);
        if (errorData.data) {
          for (const [field, message] of Object.entries(errorData.data)) {
          }
        }
      } catch (e) {
        console.error('Failed to parse error message:', e);
      }
    });
});





const getAllCustomer = () => {
  $.ajax({
    url: 'http://localhost:8080/api/v1/film/get',
    type: "GET",
    headers: {
      "Authorization": "Bearer " + localStorage.getItem("token")
    },
    success: (res) => {
      console.log(res);
      $('#customer_table_body').empty();


      res.data.forEach(customer => {
        const imageUrl = customer.imageUrl ? `data:image/png;base64,${customer.imageUrl}` : 'assests/Images/noImage.png';
        $('#customer_table_body').append(`
          <tr>
            <td>${customer.id}</td>
            <td>${customer.title}</td>
            <td>${customer.description}</td>
            <td>${customer.genre}</td>
            <td>${customer.team}</td>
            <td>${customer.durationMinutes}</td>
              <td>${customer.releaseDate}</td>
                <td>${customer.language}</td>
                  <td>${customer.cast}</td>
                    <td>${customer.trailerUrl}</td>
            <td><img src="${imageUrl}" alt="Customer Image" style="width: 100px; height: auto;"></td>
             <td>
              <button class="btn btn-light btn-sm" onclick="editCustomer('${customer.id}','${customer.title}','${customer.description}','${customer.genre}','${customer.team}','${customer.durationMinutes}','${customer.releaseDate}','${customer.language}','${customer.cast}','${customer.trailerUrl}')">
                Update
              </button>
            </td>
            <td>
              <button class="btn btn-light btn-sm" onclick="deleteCustomer('${customer.id}')">
                Delete
              </button>
            </td>
          </tr>
        `);
      });
    },
    error: (err) => {
      console.log(err);
    }
  });
};


$(document).ready(function (){
  getAllCustomer();
})





const deleteCustomer=(id) =>{
  $.ajax({
    url:`http://localhost:8080/api/v1/film/delete/${id}`,
    type:'DELETE',
    headers: {
      "Authorization": "Bearer " + localStorage.getItem("token")
    },
    success:(res)=>{
      getAllCustomer()
      console.log(res)
      alert("film deleted successfully")


    },
    error:(err) =>{
      console.log(err)
    }

  })
}


























const editCustomer=(id, title,description,genre,team,durationMinutes,releaseDate,language,cast,trailerUrl) =>{
  $('#updated_film_id').val(id)

  $('#updated_film_title').val(title)
  $('#updated_description').val(description)
  $('#updated_genre').val(genre)
  $('#updated_team').val(team)

  $('#updated_duration').val(durationMinutes)

  $('#updated_release').val(releaseDate)
  $('#updated_language').val(language)
  $('#updated_cast').val(cast)
  $('#updated_trailer').val(trailerUrl)
  $('#updateCustomerModal').modal('show')

}




$('#btn_update_customer').click((e) => {
  e.preventDefault(); // Prevent the default form submission

  // Create a new FormData object
  const updateCustomerData = new FormData();

  // Append form data to the FormData object
  updateCustomerData.append('spice', JSON.stringify({

id:  $('#updated_film_id').val(),
   title:  $('#updated_film_title').val(),
  description :  $('#updated_description').val(),
 genre :  $('#updated_genre').val(),
 team :  $('#updated_team').val(),

 durationMinutes : $('#updated_duration').val(),

      releaseDate : $('#updated_release').val(),
      language : $('#updated_language').val(),
  cast : $('#updated_cast').val(),
      trailerUrl : $('#updated_trailer').val()

  }));

  const updatedImageFile = $('#edit_image')[0].files[0]; // Get the file from the input
  if (updatedImageFile) {
    updateCustomerData.append('file', updatedImageFile); // Use 'file' as the key
  }

  // Send the FormData object
  $.ajax({
    url: 'http://localhost:8080/api/v1/film/update', // Your API endpoint
    type: 'PUT',
    headers: {
      "Authorization": "Bearer " + localStorage.getItem("token")
    },
    data: updateCustomerData,
    processData: false, // Prevent jQuery from automatically transforming the data into a query string
    contentType: false, // Set content type to false to let the browser set it
    success: (res) => {
      getAllCustomer();
      console.log('Update successful:', res);
      alert("film updated successfully")
       // Refresh the customer list
      $('#updateCustomerModal').modal('hide'); // Hide the modal after successful update
    },
    error: (err) => {
      console.error('Update failed:', err); // Log any errors
    }
  });
});

