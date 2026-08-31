import { useState } from 'react'
function Request(){

  const API_URL = import.meta.env.VITE_API_URL
  const [companyName, setCompanyName] = useState('')
  const [requesterFirstName, setRequesterFirstName] = useState('')
  const [requesterLastName, setRequesterLastName] = useState('')
  const [customerPhone, setCustomerPhone] = useState('')
  const [customerEmail, setCustomerEmail] = useState('')
  const [serviceAddress, setServiceAddress] = useState('')
  const [desiredServiceDate, setDesiredServiceDate] = useState('')
  const [numberOfUnits, setNumberOfUnits] = useState('')
  const [dopTesting, setDOPTesting] = useState(true)
  const [filterChange, setFilterChange] = useState(false)
  const [otherServiceRequest, setOtherServiceRequest] = useState(false)
  const [unitDetails, setUnitDetails] = useState('')
  const [additionalInformation, setAdditionalInformation] = useState('')
  const [successMessage, setSuccessMessage] = useState('')
  const [errorMessage, setErrorMessage] = useState('')

  async function handleSubmit(event){
    event.preventDefault()
    setSuccessMessage('')
    setErrorMessage('')
    const request = {
        companyName,
        requesterFirstName,
        requesterLastName,
        customerPhone,
        customerEmail,
        serviceAddress,
        desiredServiceDate,
        numberOfUnits: Number(numberOfUnits),
        dopTesting,
        filterChange,
        otherServiceRequest,
        unitDetails,
        additionalInformation
    }
    try{
      const response = await fetch(`${API_URL}/api/requests`, {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(request)})

    if (response.ok){
      const savedRequest = await response.json()
      console.log(savedRequest)
      setSuccessMessage('Request Submitted Successfully')
      setCompanyName('')
      setRequesterFirstName('')
      setRequesterLastName('')
      setCustomerPhone('')
      setCustomerEmail('')
      setServiceAddress('')
      setDesiredServiceDate('')
      setNumberOfUnits('')
      setDOPTesting(true)
      setFilterChange(false)
      setOtherServiceRequest(false)
      setUnitDetails('')
      setAdditionalInformation('')
    }
    else{
      setErrorMessage('There was a problem submitting the request.')
    }
  } 
    catch(error){console.log(error)
    setErrorMessage('Unable to connect to the server.')
  }
}
  return(
     <div>
      <h1>On Site DOP Service Request</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Company Name</label>
          <input type="text" value={companyName} onChange={(event) => setCompanyName(event.target.value)} required />
        </div>
          <div>
          <label>First Name</label>
          <input type="text" value={requesterFirstName} onChange={(event) => setRequesterFirstName(event.target.value)} required />
        </div>
        <div>
          <label>Last Name</label>
          <input type="text" value={requesterLastName} onChange={(event) => setRequesterLastName(event.target.value)} required />
        </div>  
        <div>
          <label>Phone Number</label>
          <input type="tel" value={customerPhone} onChange={(event) => setCustomerPhone(event.target.value)} required />
        </div>
        <div>
          <label>Email</label>
          <input type="email" value={customerEmail} onChange={(event) => setCustomerEmail(event.target.value)} required />
        </div>
         <div>
          <label>Service Address</label>
          <input type="text" value={serviceAddress} onChange={(event) => setServiceAddress(event.target.value)} required />
        </div>
         <div>
          <label>Requested Service Date</label>
          <input type="date" value={desiredServiceDate} onChange={(event) => setDesiredServiceDate(event.target.value)} required />
        </div>
         <div>
          <label>Number of Units</label>
          <input type="number" value={numberOfUnits} onChange={(event) => setNumberOfUnits(event.target.value)} required />
        </div>
        <div>
          <label>
          <input type="checkbox" checked={dopTesting} onChange={(event) => setDOPTesting(event.target.checked)}/>
          DOP Testing</label>
        </div>
        <div>
          <label>
          <input type="checkbox" checked={filterChange} onChange={(event) => setFilterChange(event.target.checked)}/>
          Filter Change</label>
        </div>
        <div>
          <label>
          <input type="checkbox" checked={otherServiceRequest} onChange={(event) => setOtherServiceRequest(event.target.checked)}/>
          Other Service Request </label>
        </div>
        <div>
          <label>Unit Details</label>
          <textarea value={unitDetails} onChange={(event) => setUnitDetails(event.target.value)}/>
        </div>
        <div>
          <label>Additional Information</label>
          <textarea value={additionalInformation} onChange={(event) => setAdditionalInformation(event.target.value)}/>
        </div>
        <button type="submit">Submit Request</button>
        {successMessage && <p>{successMessage}</p>}
        {errorMessage && <p>{errorMessage}</p>}
      </form>     
    </div>
  )
}

export default Request